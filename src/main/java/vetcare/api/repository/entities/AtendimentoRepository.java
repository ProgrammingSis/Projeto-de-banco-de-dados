package vetcare.api.repository.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import vetcare.api.config.MailConfig;
import vetcare.api.model.dto.AtendimentoPetDTO;
import vetcare.api.model.dto.ConsultaDTO;
import vetcare.api.model.dto.NotificacaoDTO;
import vetcare.api.model.entities.Atendimento;
import vetcare.api.repository.mapper.dto.AtentimentoPetDTORowMapper;
import vetcare.api.repository.mapper.entities.AtendimentoRowMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AtendimentoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MailConfig mailConfig;

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Atendimento atendimento) {
        String sql = "INSERT INTO Atendimento (data, fk_tipo, id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                atendimento.getDate(),
                atendimento.getTipoAtendimento(),
                atendimento.getIdAtendimento()
        );
    }

    // READ
    public Atendimento findById(Long id) {
        String sql = "SELECT * FROM Atendimento WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AtendimentoRowMapper(), id);
    }

    public List<Atendimento> findAll() {
        String sql = "SELECT * FROM Atendimento";
        return jdbcTemplate.query(sql, new AtendimentoRowMapper());
    }

    public List<AtendimentoPetDTO> findAllPetAtendimentos(Integer animalId) {
        String sql = """
                SELECT
                    Atendimento.id,
                    Atendimento.data,
                    Atendimento.horario,
                    Atendimento.fk_tipo,
                    AtendidoEm.fk_animal_id,
                    AtendidoEm.fk_veterinario_crmv 
                            FROM
                    Atendimento
                            JOIN
                    AtendidoEm
                            ON
                    Atendimento.id = AtendidoEm.fk_atendimento_id 
                            WHERE  AtendidoEm.fk_animal_id = ?;
                """;
        return jdbcTemplate.query(sql, new AtentimentoPetDTORowMapper(), animalId);
    }

    // UPDATE
    public int update(Atendimento atendimento) {
        String sql = "UPDATE Atendimento SET data = ?, fk_tipo = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                atendimento.getDate(),
                atendimento.getTipoAtendimento(),
                atendimento.getIdAtendimento()
        );
    }

    // DELETE
    public int deleteById(Long id) {
        String sql = "DELETE FROM Atendimento WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // CRIAR NOVA CONSULTA
    public boolean agendarConsulta(AtendimentoPetDTO atendimentoPetDTO) {
        String sqlAtendimento = "INSERT INTO Atendimento (data, fk_tipo, horario) VALUES (?, ?, ?)";
        String sqlAtendidoEm = "INSERT INTO AtendidoEm (fk_Veterinario_crmv, fk_Atendimento_id, fk_Animal_id) VALUES (?, ?, ?)";

        try {
            // KeyHolder para capturar o ID gerado automaticamente
            KeyHolder keyHolder = new GeneratedKeyHolder();

            // Inserir na tabela Atendimento e capturar o ID gerado
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sqlAtendimento, new String[] { "id" });
                ps.setDate(1, atendimentoPetDTO.getDate()); // Deve ser java.sql.Date
                ps.setString(2, atendimentoPetDTO.getTipoAtendimento()); // fk_tipo
                ps.setTime(3, atendimentoPetDTO.getHorario()); // Deve ser java.sql.Time
                return ps;
            }, keyHolder);

            // Recuperar o ID gerado
            int generatedId = keyHolder.getKey().intValue();

            // Inserir na tabela AtendidoEm
            jdbcTemplate.update(sqlAtendidoEm,
                    atendimentoPetDTO.getCrmvVet(), // CRMV do veterinário
                    generatedId, // ID do atendimento gerado
                    atendimentoPetDTO.getIdPet() // ID do animal
            );

            System.out.println("Consulta agendada com sucesso!");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao agendar consulta: " + e.getMessage());
            return false;
        }
    }




    public boolean deletarConsulta(Long idAtendimento) {
        String deleteDependentesSql = "DELETE FROM AtendimentosFaturas WHERE fk_Atendimento_id = ?";
        String sqlAtendidoEm = "DELETE FROM AtendidoEm WHERE fk_Atendimento_id = ?";
        String sqlAtendimento = "DELETE FROM Atendimento WHERE id = ?";

        try {

            jdbcTemplate.update(deleteDependentesSql, idAtendimento);

            // Deletar da tabela AtendidoEm
            jdbcTemplate.update(sqlAtendidoEm, idAtendimento);

            // Deletar da tabela Atendimento
            jdbcTemplate.update(sqlAtendimento, idAtendimento);

            System.out.println("Consulta deletada com sucesso!");
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao deletar consulta: " + e.getMessage());
            return false;
        }
    }


    public List<ConsultaDTO> getCalendarioVeterinario(String crmv, LocalDate dataInicio, LocalDate dataFim) {
        String sql = """
        SELECT 
            a.data, 
            a.horario, 
            an.nome AS nomeAnimal, 
            an.raca, 
            c.nome AS nomeCliente, 
            c.contato 
        FROM Atendimento a
        JOIN AtendidoEm ae ON a.id = ae.fk_Atendimento_id
        JOIN Animal an ON ae.fk_Animal_id = an.id
        JOIN Cliente c ON an.fk_Cliente_cpf = c.cpf
        WHERE ae.fk_Veterinario_crmv = ?
          AND a.data BETWEEN ? AND ?
        ORDER BY a.data, a.horario
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ConsultaDTO consulta = new ConsultaDTO();
            consulta.setData((rs.getDate("data").toLocalDate()));
            var time = rs.getTime("horario");
            if (time != null) consulta.setHorario(time.toLocalTime());
            consulta.setNomeAnimal(rs.getString("nomeAnimal"));
            consulta.setRaca(rs.getString("raca"));
            consulta.setNomeCliente(rs.getString("nomeCliente"));
            consulta.setContatoCliente(rs.getString("contato"));
            return consulta;
        }, crmv, Date.valueOf(dataInicio), Date.valueOf(dataFim));
    }

    public boolean criarNotificacoes() {
        String sql = """
        SELECT 
            a.id, 
            a.data, 
            a.horario, 
            c.nome AS nomeCliente, 
            an.nome AS nomePet, 
            c.contato AS contatoCliente, 
            v.nome AS nomeVeterinario, 
            v.contato AS contatoVeterinario 
        FROM Atendimento a
        JOIN AtendidoEm ae ON a.id = ae.fk_Atendimento_id
        JOIN Animal an ON an.id = ae.fk_Animal_id
        JOIN Cliente c ON c.cpf = an.fk_Cliente_cpf
        JOIN Veterinario v ON v.crmv = ae.fk_Veterinario_crmv
        WHERE a.data = ?;
    """;


        LocalDate amanha = LocalDate.now().plusDays(1);

        List<NotificacaoDTO> notificacoes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            NotificacaoDTO notificacao = new NotificacaoDTO();
            notificacao.setIdAtendimento(rs.getInt("id"));
            notificacao.setData(rs.getDate("data").toLocalDate());
            notificacao.setHorario(rs.getTime("horario"));
            notificacao.setNomeCliente(rs.getString("nomeCliente"));
            notificacao.setContatoCliente(rs.getString("contatoCliente"));
            notificacao.setNomeVeterinario(rs.getString("nomeVeterinario"));
            notificacao.setContatoVeterinario(rs.getString("contatoVeterinario"));
            notificacao.setNomeAnimal(rs.getString("nomePet"));

            return notificacao;

        }, Date.valueOf(amanha));

        notificacoes.forEach(this::enviarNotificacaoPorEmail);

        return true;
    }

    public void enviarNotificacaoPorEmail(NotificacaoDTO notificacao) {
        String assunto = "Lembrete de Consulta Agendada - " + notificacao.getNomeAnimal();
        String corpoMensagemCliente = "Olá " + notificacao.getNomeCliente() + ",\n\n" +
                "Lembrete: sua consulta para o animal " + notificacao.getNomeAnimal() + " está agendada para amanhã, às " +
                notificacao.getHorario() + ".\n\n" +
                "Atenciosamente,\nClínica Veterinária";

        String corpoMensagemVeterinario = "Olá " + notificacao.getNomeVeterinario() + ",\n\n" +
                "Lembrete: você tem uma consulta agendada para o animal " + notificacao.getNomeAnimal() +
                " amanhã, às " + notificacao.getHorario() + ".\n\n" +
                "Atenciosamente,\nClínica Veterinária";

        // Enviar e-mail para o cliente
        mailConfig.enviarNotificacao(notificacao.getContatoCliente(), assunto, corpoMensagemCliente);

        // Enviar e-mail para o veterinário
        mailConfig.enviarNotificacao(notificacao.getContatoVeterinario(), assunto, corpoMensagemVeterinario);
    }
}