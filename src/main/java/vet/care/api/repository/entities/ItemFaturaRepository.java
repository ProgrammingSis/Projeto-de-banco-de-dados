package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.ItemFatura;
import vet.care.api.repository.mapper.entities.ItemFaturaRowMapper;

import java.util.List;

@Repository
public class ItemFaturaRepository {
    private final JdbcTemplate jdbcTemplate;

    public ItemFaturaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(ItemFatura itemFatura) {
        String sql = """
            INSERT INTO Item_Fatura (quantidade, id, fk_idFatura, fk_Insumo_ean) 
            VALUES (?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                itemFatura.getQuantItens(),
                itemFatura.getIdItem(),
                itemFatura.getIdFatura(),
                itemFatura.getCdInsumo()
        );
    }

    // READ
    public ItemFatura findById(Long id) {
        String sql = "SELECT * FROM Item_Fatura WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ItemFaturaRowMapper(), id);
    }

    public List<ItemFatura> findAll() {
        String sql = "SELECT * FROM Item_Fatura";
        return jdbcTemplate.query(sql, new ItemFaturaRowMapper());
    }

    // UPDATE
    public int update(ItemFatura itemFatura) {
        String sql = """
            UPDATE Item_Fatura 
            SET quantidade = ?, id = ?, fk_idFatura = ?, fk_Insumo_ean = ?
            WHERE id = ?
        """;
        return jdbcTemplate.update(sql,
                itemFatura.getQuantItens(),
                itemFatura.getIdItem(),
                itemFatura.getIdFatura(),
                itemFatura.getCdInsumo()
        );
    }

    // DELETE
    public int deleteById(Long id) {
        String sql = "DELETE FROM Item_Fatura WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
