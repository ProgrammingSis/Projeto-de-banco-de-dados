package vet.care.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vet.care.api.model.entities.Atendimento;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendimentoRowMapper implements RowMapper<Atendimento> {
    @Override
    public Atendimento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Atendimento atendimento = new Atendimento();
        atendimento.setDate(rs.getDate("date"));
        atendimento.setTipoAtendimento(rs.getString("fk_tipo"));
        atendimento.setIdAtendimento(rs.getLong("id"));

        return atendimento;
    }
}
