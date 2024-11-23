package vet.care.api.repository.mapper.dto;

import org.springframework.jdbc.core.RowMapper;
import vet.care.api.model.dto.DonoPetDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DonoPetRowMapper implements RowMapper<DonoPetDTO> {
    @Override
    public DonoPetDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        DonoPetDTO donoPetDTO = new DonoPetDTO();
        donoPetDTO.setNomePet(rs.getString("nome"));
        donoPetDTO.setRacaPet(rs.getString("raca"));
        donoPetDTO.setNomeCliente(rs.getString("nome"));
        donoPetDTO.setContatoCliente(rs.getString("contato"));

        return donoPetDTO;
    }
}
