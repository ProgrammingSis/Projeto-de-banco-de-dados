package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.VacinaPet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacinaPetRowMapper implements RowMapper<VacinaPet> {
    @Override
    public VacinaPet mapRow(ResultSet rs, int rowNum) throws SQLException {
        VacinaPet vacinaPet = new VacinaPet();
        vacinaPet.setIdVacina(rs.getString("nome"));
        vacinaPet.setIdAtendimento(rs.getInt("fk_Atendimento_id"));
        vacinaPet.setDataVacina(rs.getDate("data"));

        return vacinaPet;
    }
}