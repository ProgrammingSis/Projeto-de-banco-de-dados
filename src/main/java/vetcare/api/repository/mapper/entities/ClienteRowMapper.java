package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCpfCliente(rs.getString("cpf"));
        cliente.setNomeCliente(rs.getString("nome"));
        cliente.setEndCliente(rs.getString("endereco"));
        cliente.setContatoCliente(rs.getString("contato"));

        return cliente;
    }
}
