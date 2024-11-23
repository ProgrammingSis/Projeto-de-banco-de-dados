package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.AtendidoEm;
import vet.care.api.repository.mapper.entities.AtendidoEmRowMapper;

import java.util.List;

@Repository
public class AtendidoEmRepository {
    private final JdbcTemplate jdbcTemplate;

    public AtendidoEmRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(AtendidoEm atendidoEm) {
        String sql = "INSERT INTO AtendidoEm (fk_Veterinario_crmv, fk_Atendimento_id, fk_Animal_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                atendidoEm.getCrmvVet(),
                atendidoEm.getIdAtendimento(),
                atendidoEm.getIdPet()
        );
    }

    // READ
    public AtendidoEm findByAtendimentoId(Long idAtendimento) {
        String sql = "SELECT * FROM AtendidoEm WHERE fk_Atendimento_id = ?";
        return jdbcTemplate.queryForObject(sql, new AtendidoEmRowMapper(), idAtendimento);
    }

    public List<AtendidoEm> findAll() {
        String sql = "SELECT * FROM AtendidoEm";
        return jdbcTemplate.query(sql, new AtendidoEmRowMapper());
    }

    // UPDATE
    public int update(AtendidoEm atendidoEm) {
        String sql = """
            UPDATE AtendidoEm 
            SET fk_Veterinario_crmv = ?, fk_Animal_id = ? 
            WHERE fk_Atendimento_id = ?
        """;
        return jdbcTemplate.update(sql,
                atendidoEm.getCrmvVet(),
                atendidoEm.getIdPet(),
                atendidoEm.getIdAtendimento()
        );
    }

    // DELETE
    public int deleteByAtendimentoId(Long idAtendimento) {
        String sql = "DELETE FROM AtendidoEm WHERE fk_Atendimento_id = ?";
        return jdbcTemplate.update(sql, idAtendimento);
    }
}
