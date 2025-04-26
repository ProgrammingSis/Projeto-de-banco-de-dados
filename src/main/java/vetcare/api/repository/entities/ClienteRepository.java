package vetcare.api.repository.entities;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.controller.PacientesController;
import vetcare.api.model.entities.Cliente;
import vetcare.api.repository.mapper.entities.AnimalRowMapper;
import vetcare.api.repository.mapper.entities.ClienteRowMapper;

import java.util.List;

@Repository
public class ClienteRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf, nome, endereco, contato) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                cliente.getCpfCliente(),
                cliente.getNomeCliente(),
                cliente.getEndCliente(),
                cliente.getContatoCliente()
        );
    }

    // READ
    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteRowMapper(), cpf);
    }

    public List<Cliente> findAll(String nome) {
        String sql = "SELECT * FROM Cliente WHERE nome ILIKE ?";
        String namePattern = "%" + nome + "%"; // Adiciona os curingas para busca parcial

        return jdbcTemplate.query(sql, new ClienteRowMapper(), namePattern);
    }

    // UPDATE
    public int update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, endereco = ?, contato = ? WHERE cpf = ?";
        return jdbcTemplate.update(sql,
                cliente.getNomeCliente(),
                cliente.getEndCliente(),
                cliente.getContatoCliente(),
                cliente.getCpfCliente()
        );
    }

    // DELETE
    public int deleteByCpf(String cpf) {
        String sqle = "DELETE FROM AtendimentosFaturas" +
                " WHERE fk_Fatura_id IN (" +
                "SELECT id FROM Fatura WHERE fk_Cliente_cpf = ?" +
                ");";

        String sqld = "DELETE FROM Item_Fatura " +
                "WHERE fk_idFatura IN (" +
                "SELECT id FROM Fatura WHERE fk_Cliente_cpf = ?" +
                ");";
        String sqlc = "DELETE FROM AtendidoEm " +
                "WHERE fk_Animal_id IN (" +
                "SELECT id FROM Animal WHERE fk_Cliente_cpf = ?" +
                ");";
        String sqla = "DELETE FROM Animal WHERE fk_Cliente_cpf = ?";
        String sqlb = "DELETE FROM Fatura WHERE fk_Cliente_cpf = ?";
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        jdbcTemplate.update(sqle, cpf);
        jdbcTemplate.update(sqld, cpf);
        jdbcTemplate.update(sqlc, cpf);
        jdbcTemplate.update(sqla, cpf);
        jdbcTemplate.update(sqlb, cpf);
        return jdbcTemplate.update(sql, cpf);
    }

    public String buscarEmailClientePorAnimalId(Integer animalId) {

        String sql = "SELECT c.contato FROM Cliente c " +
                "JOIN Animal a ON a.fk_Cliente_cpf = c.cpf " +
                "WHERE a.id = ?";

        return jdbcTemplate.queryForObject(sql, String.class, animalId);
    }
}
