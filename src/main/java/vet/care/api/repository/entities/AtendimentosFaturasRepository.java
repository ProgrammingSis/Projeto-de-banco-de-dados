package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.AtendimentosFaturas;
import vet.care.api.repository.mapper.entities.AtendimentosFaturasRowMapper;

import java.util.List;

@Repository
public class AtendimentosFaturasRepository {
    private final JdbcTemplate jdbcTemplate;

    public AtendimentosFaturasRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(AtendimentosFaturas atendimentosFaturas) {
        String sql = "INSERT INTO AtendimentosFaturas (fk_Item_Fatura_id, fk_Atendimento_id, fk_Fatura_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                atendimentosFaturas.getIdItemFatura(),
                atendimentosFaturas.getIdAtendimento(),
                atendimentosFaturas.getIdFatura()
        );
    }

    // READ
    public AtendimentosFaturas findByItemFaturaId(Long idItemFatura) {
        String sql = "SELECT * FROM AtendimentosFaturas WHERE fk_Item_Fatura_id = ?";
        return jdbcTemplate.queryForObject(sql, new AtendimentosFaturasRowMapper(), idItemFatura);
    }

    public List<AtendimentosFaturas> findAll() {
        String sql = "SELECT * FROM AtendimentosFaturas";
        return jdbcTemplate.query(sql, new AtendimentosFaturasRowMapper());
    }

    // UPDATE
    public int update(AtendimentosFaturas atendimentosFaturas) {
        String sql = "UPDATE AtendimentosFaturas SET fk_Atendimento_id = ?, fk_Fatura_id = ? WHERE fk_Item_Fatura_id = ?";
        return jdbcTemplate.update(sql,
                atendimentosFaturas.getIdAtendimento(),
                atendimentosFaturas.getIdFatura(),
                atendimentosFaturas.getIdItemFatura()
        );
    }

    // DELETE
    public int deleteByItemFaturaId(Long idItemFatura) {
        String sql = "DELETE FROM AtendimentosFaturas WHERE fk_Item_Fatura_id = ?";
        return jdbcTemplate.update(sql, idItemFatura);
    }
}
