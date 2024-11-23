package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.Fornecimento;
import vet.care.api.repository.mapper.entities.FornecimentoRowMapper;

import java.util.List;

@Repository
public class FornecimentoRepository {

    private final JdbcTemplate jdbcTemplate;

    public FornecimentoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */


    // CREATE
    public int save(Fornecimento fornecimento) {
        String sql = """
            INSERT INTO Fornecimento (id, quantidade, preco, fk_Fornecedor_cnpj, fk_Insumo_ean) 
            VALUES (?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                fornecimento.getIdCompra(),
                fornecimento.getQuantItens(),
                fornecimento.getValorTotal(),
                fornecimento.getFornecedorCnpj(),
                fornecimento.getCdInsumo()
        );
    }

    // READ
    public Fornecimento findById(String fornecedorCnpj, String cdInsumo) {
        String sql = "SELECT * FROM Fornecimento WHERE fk_Fornecedor_cnpj = ? AND fk_Insumo_ean = ?";
        return jdbcTemplate.queryForObject(sql, new FornecimentoRowMapper(), fornecedorCnpj, cdInsumo);
    }

    public List<Fornecimento> findAll() {
        String sql = "SELECT * FROM Fornecimento";
        return jdbcTemplate.query(sql, new FornecimentoRowMapper());
    }

    // UPDATE
    public int update(Fornecimento fornecimento) {
        String sql = """
            UPDATE Fornecimento 
            SET quantidade = ?, preco = ? 
            WHERE fk_Fornecedor_cnpj = ? AND fk_Insumo_ean = ?
        """;
        return jdbcTemplate.update(sql,
                fornecimento.getQuantItens(),
                fornecimento.getValorTotal(),
                fornecimento.getFornecedorCnpj(),
                fornecimento.getCdInsumo()
        );
    }

    // DELETE
    public int deleteById(Long id, String fornecedorCnpj, String cdInsumo) {
        String sql = "DELETE FROM Fornecimento WHERE id = ? AND fk_Fornecedor_cnpj = ? AND fk_Insumo_ean = ?";
        return jdbcTemplate.update(sql, id, fornecedorCnpj, cdInsumo);
    }
}
