package vet.care.api.repository.mapper.dto;

import org.springframework.jdbc.core.RowMapper;
import vet.care.api.model.dto.AnimalClienteDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalClienteDTORowMapper implements RowMapper<AnimalClienteDTO> {
    @Override
    public AnimalClienteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AnimalClienteDTO animalClienteDTO = new AnimalClienteDTO();
        animalClienteDTO.setIdPet(rs.getLong("id"));
        animalClienteDTO.setNomePet(rs.getString("nome"));
        animalClienteDTO.setRacaPet(rs.getString("raca"));
        animalClienteDTO.setPesoPet(rs.getDouble("peso"));
        animalClienteDTO.setCpfDonoPet(rs.getString("fk_cliente_cpf"));
        animalClienteDTO.setNomeCliente(rs.getString("nome"));
        animalClienteDTO.setEndCliente(rs.getString("endereco"));
        animalClienteDTO.setContatoCliente(rs.getString("contato"));

        return animalClienteDTO;
    }
}
