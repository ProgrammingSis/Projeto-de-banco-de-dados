package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Fatura;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FaturaRowMapper implements RowMapper<Fatura> {
    @Override
    public Fatura mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fatura fatura = new Fatura();
        fatura.setValorTotal(rs.getDouble("valor"));
        fatura.setDate(rs.getDate("data"));
        fatura.setFormaPagamento(rs.getString("fk_formaPagamento"));
        fatura.setIdFatura(rs.getInt("id"));
        fatura.setClienteCpf(rs.getString("fk_cliente_cpf"));

        return fatura;
    }
}
