package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Insumo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsumoRowMapper implements RowMapper<Insumo> {
    @Override
    public Insumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setNomeInsumo(rs.getString("nome"));
        insumo.setCdInsumo(rs.getString("ean"));
        insumo.setValorInsumo(rs.getDouble("preco"));
        insumo.setTipoInsumo(rs.getString("fk_tipo"));
        insumo.setQuantInsumo(rs.getLong("quantidade"));
        insumo.setDate(rs.getDate("data"));

        return insumo;
    }
}
