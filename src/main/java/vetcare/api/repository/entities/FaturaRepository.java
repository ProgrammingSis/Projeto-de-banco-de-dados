package vetcare.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.model.entities.Fatura;
import vetcare.api.repository.mapper.entities.FaturaRowMapper;

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
            INSERT INTO Fatura (valor, data, formaPagamento, id, fk_Cliente_cpf) 
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
}
