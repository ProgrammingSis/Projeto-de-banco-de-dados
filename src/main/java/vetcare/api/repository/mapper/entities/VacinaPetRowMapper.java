package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.VacinaPet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VacinaPetRowMapper implements RowMapper<VacinaPet> {
    @Override
    public VacinaPet mapRow(ResultSet rs, int rowNum) throws SQLException {
        VacinaPet vacinaPet = new VacinaPet();
        vacinaPet.setVacinaNome(rs.getString("vacinaNome"));
        vacinaPet.setIdAtendimento(rs.getInt("idAtendimento"));
        vacinaPet.setDataVacina(rs.getDate("dataVacina"));
        vacinaPet.setDataReforco(rs.getDate("dataReforco"));

        return vacinaPet;
    }
}