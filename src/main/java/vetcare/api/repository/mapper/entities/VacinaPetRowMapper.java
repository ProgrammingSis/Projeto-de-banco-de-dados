package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.VacinaPet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacinaPetRowMapper implements RowMapper<VacinaPet> {
    @Override
    public VacinaPet mapRow(ResultSet rs, int rowNum) throws SQLException {
        VacinaPet vacinaPet = new VacinaPet();
        vacinaPet.setIdVacina(rs.getLong("fk_idVacina"));
        vacinaPet.setIdAtendimento(rs.getLong("fk_Atendimento_id"));

        return vacinaPet;
    }
}