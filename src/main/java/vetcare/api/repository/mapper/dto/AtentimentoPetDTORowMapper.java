package vetcare.api.repository.mapper.dto;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.dto.AtendimentoPetDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtentimentoPetDTORowMapper implements RowMapper<AtendimentoPetDTO> {
    @Override
    public AtendimentoPetDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AtendimentoPetDTO atendimentoPetDTO = new AtendimentoPetDTO();
        atendimentoPetDTO.setCrmvVet(rs.getString("fk_Veterinario_crmv"));
        atendimentoPetDTO.setIdAtendimento(rs.getInt("id"));
        atendimentoPetDTO.setIdPet(rs.getInt("fk_Animal_id"));
        atendimentoPetDTO.setDate(rs.getDate("data"));
        atendimentoPetDTO.setTipoAtendimento(rs.getString("fk_tipo"));
        atendimentoPetDTO.setHorario(rs.getTime("horario"));
        return null;
    }
}
