package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Veterinario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VeterinarioRowMapper implements RowMapper<Veterinario> {
    @Override
    public Veterinario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Veterinario veterinario = new Veterinario();
        veterinario.setCrmvVet(rs.getString("crmv"));
        veterinario.setNomeVet(rs.getString("nome"));
        veterinario.setTipoVet(rs.getString("fk_especialidade"));
        veterinario.setContato(rs.getString("contato"));

        return veterinario;
    }
}
