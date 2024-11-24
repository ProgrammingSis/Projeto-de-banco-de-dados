package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Fornecedor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorRowMapper implements RowMapper<Fornecedor> {
    @Override
    public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpjFornecedor(rs.getString("cnpj"));
        fornecedor.setContatoFornecedor(rs.getString("contato"));
        fornecedor.setEndFornecedor(rs.getString("endereco"));
        fornecedor.setNomeFornecedor(rs.getString("nome"));

        return fornecedor;
    }
}
