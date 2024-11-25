package vetcare.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.model.dto.FaturaClienteDTO;
import vetcare.api.model.entities.Fatura;
import vetcare.api.repository.mapper.entities.FaturaRowMapper;

import java.sql.Date;
import java.util.List;

@Repository
public class FaturaRepository {

    private final JdbcTemplate jdbcTemplate;

    public FaturaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Fatura fatura) {
        String sql = """
            INSERT INTO Fatura (valor, data, fk_formaPagamento, id, fk_cliente_cpf) 
            VALUES (?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                fatura.getValorTotal(),
                fatura.getDate(),
                fatura.getFormaPagamento(),
                fatura.getIdFatura(),
                fatura.getClienteCpf()
        );
    }

    // READ
    public Fatura findById(Long id) {
        String sql = "SELECT * FROM Fatura WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new FaturaRowMapper(), id);
    }

    public Fatura findByCpf(String cpf) {
        String sql = "SELECT * FROM Fatura WHERE fk_Cliente_cpf = ?";
        return jdbcTemplate.queryForObject(sql, new FaturaRowMapper(), cpf);
    }

    public List<Fatura> findAll() {
        String sql = "SELECT * FROM Fatura";
        return jdbcTemplate.query(sql, new FaturaRowMapper());
    }

    // UPDATE
    public int update(Fatura fatura) {
        String sql = """
            UPDATE Fatura 
            SET valor = ?, data = ?, formaPagamento = ?, fk_Cliente_cpf = ? 
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql,
                fatura.getValorTotal(),
                fatura.getDate(),
                fatura.getFormaPagamento(),
                fatura.getClienteCpf(),
                fatura.getIdFatura()
        );
    }

    // DELETE
    public int deleteById(Long id) {
        String sql = "DELETE FROM Fatura WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }


    public FaturaClienteDTO enviarComprovanteFatura(int faturaId) {
            String sqlFatura = """
            SELECT f.valor, f.data, f.fk_formaPagamento, c.nome, c.contato 
            FROM Fatura f
            JOIN Cliente c ON f.fk_Cliente_cpf = c.cpf
            WHERE f.id = ?
        """;

            FaturaClienteDTO faturaComCliente = jdbcTemplate.queryForObject(sqlFatura, (rs, rowNum) -> {
                FaturaClienteDTO fc = new FaturaClienteDTO();
                fc.setValorTotal(rs.getDouble("valor"));
                fc.setData(Date.valueOf(rs.getDate("data").toLocalDate()));
                fc.setFormaPagamento(rs.getString("fk_formaPagamento"));
                fc.setClienteNome(rs.getString("nome"));
                fc.setClienteContato(rs.getString("contato"));
                return fc;
            }, faturaId);

        return faturaComCliente;
    }

}
