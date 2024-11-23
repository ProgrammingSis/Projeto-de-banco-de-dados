package vet.care.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vet.care.api.model.entities.Fornecimento;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecimentoRowMapper implements RowMapper<Fornecimento> {
    @Override
    public Fornecimento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fornecimento fornecimento = new Fornecimento();
        fornecimento.setQuantItens(rs.getLong("quantidade"));
        fornecimento.setValorTotal(rs.getDouble("preco"));
        fornecimento.setFornecedorCnpj(rs.getString("fk_Fornecedor_cnpj"));
        fornecimento.setCdInsumo(rs.getString("fk_Insumo_ean"));
        fornecimento.setIdCompra(rs.getLong("id"));

        return fornecimento;
    }
}
