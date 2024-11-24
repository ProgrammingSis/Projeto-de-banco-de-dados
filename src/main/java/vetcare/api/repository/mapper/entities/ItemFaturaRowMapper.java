package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.ItemFatura;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFaturaRowMapper implements RowMapper<ItemFatura> {
    @Override
    public ItemFatura mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItemFatura itemFatura = new ItemFatura();
        itemFatura.setQuantItens(rs.getLong("quantidade"));
        itemFatura.setIdItem(rs.getLong("id"));
        itemFatura.setIdFatura(rs.getLong("fk_idFatura"));
        itemFatura.setCdInsumo(rs.getLong("fk_Insumo_ean"));

        return itemFatura;
    }
}
