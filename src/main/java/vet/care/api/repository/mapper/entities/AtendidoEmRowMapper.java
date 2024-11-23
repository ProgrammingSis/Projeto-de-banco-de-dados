package vet.care.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vet.care.api.model.entities.AtendidoEm;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendidoEmRowMapper implements RowMapper<AtendidoEm> {
    @Override
    public AtendidoEm mapRow(ResultSet rs, int rowNum) throws SQLException {
        AtendidoEm atendidoEm = new AtendidoEm();
        atendidoEm.setCrmvVet(rs.getString("fk_Veterinario_crmv"));
        atendidoEm.setIdAtendimento(rs.getLong("fk_Atendimento_id"));
        atendidoEm.setIdPet(rs.getLong("fk_Animal_id"));

        return atendidoEm;
    }
}
