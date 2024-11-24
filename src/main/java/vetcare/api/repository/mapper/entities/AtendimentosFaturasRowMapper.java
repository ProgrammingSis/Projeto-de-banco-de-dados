package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.AtendimentosFaturas;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendimentosFaturasRowMapper implements RowMapper<AtendimentosFaturas> {
    @Override
    public AtendimentosFaturas mapRow(ResultSet rs, int rowNum) throws SQLException {
        AtendimentosFaturas atendimentosFaturas = new AtendimentosFaturas();
        atendimentosFaturas.setIdAtendimento(rs.getLong("fk_Atendimento_id"));
        atendimentosFaturas.setIdFatura(rs.getLong("fk_Fatura_id"));

        return atendimentosFaturas;
    }
}
