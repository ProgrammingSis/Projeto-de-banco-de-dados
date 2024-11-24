package vetcare.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.model.entities.Fornecedor;
import vetcare.api.repository.mapper.entities.FornecedorRowMapper;

import java.util.List;

@Repository
public class FornecedorRepository {
    private final JdbcTemplate jdbcTemplate;

    public FornecedorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Fornecedor fornecedor) {
        String sql = """
            INSERT INTO Fornecedor (cnpj, contato, endereco, nome) 
            VALUES (?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                fornecedor.getCnpjFornecedor(),
                fornecedor.getContatoFornecedor(),
                fornecedor.getEndFornecedor(),
                fornecedor.getNomeFornecedor()
        );
    }

    // READ
    public Fornecedor findById(String cnpj) {
        String sql = "SELECT * FROM Fornecedor WHERE cnpj = ?";
        return jdbcTemplate.queryForObject(sql, new FornecedorRowMapper(), cnpj);
    }

    public List<Fornecedor> findAll() {
        String sql = "SELECT * FROM Fornecedor";
        return jdbcTemplate.query(sql, new FornecedorRowMapper());
    }

    // UPDATE
    public int update(Fornecedor fornecedor) {
        String sql = """
            UPDATE Fornecedor 
            SET contato = ?, endereco = ?, nome = ? 
            WHERE cnpj = ?
        """;
        return jdbcTemplate.update(sql,
                fornecedor.getContatoFornecedor(),
                fornecedor.getEndFornecedor(),
                fornecedor.getNomeFornecedor(),
                fornecedor.getCnpjFornecedor()
        );
    }

    // DELETE
    public int deleteById(String cnpj) {
        String sql = "DELETE FROM Fornecedor WHERE cnpj = ?";
        return jdbcTemplate.update(sql, cnpj);
    }
}
