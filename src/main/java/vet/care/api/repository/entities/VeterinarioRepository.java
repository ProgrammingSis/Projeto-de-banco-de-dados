package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.Veterinario;
import vet.care.api.repository.mapper.entities.VeterinarioRowMapper;

import java.util.List;

@Repository
public class VeterinarioRepository {

    private final JdbcTemplate jdbcTemplate;

    public VeterinarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Veterinario veterinario) {
        String sql = "INSERT INTO Veterinario (crmv, nome, especialidade) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                veterinario.getCrmvVet(),
                veterinario.getNomeVet(),
                veterinario.getTipoVet()
        );
    }

    // READ
    public Veterinario findByCrmv(String crmv) {
        String sql = "SELECT * FROM Veterinario WHERE crmv = ?";
        return jdbcTemplate.queryForObject(sql, new VeterinarioRowMapper(), crmv);
    }

    public List<Veterinario> findAll() {
        String sql = "SELECT * FROM Veterinario";
        return jdbcTemplate.query(sql, new VeterinarioRowMapper());
    }

    // UPDATE
    public int update(Veterinario veterinario) {
        String sql = "UPDATE Veterinario SET nome = ?, especialidade = ? WHERE crmv = ?";
        return jdbcTemplate.update(sql,
                veterinario.getNomeVet(),
                veterinario.getTipoVet(),
                veterinario.getCrmvVet()
        );
    }

    // DELETE
    public int deleteByCrmv(String crmv) {
        String sql = "DELETE FROM Veterinario WHERE crmv = ?";
        return jdbcTemplate.update(sql, crmv);
    }
}
