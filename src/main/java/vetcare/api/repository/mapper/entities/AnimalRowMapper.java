package vetcare.api.repository.mapper.entities;

import org.springframework.jdbc.core.RowMapper;
import vetcare.api.model.entities.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalRowMapper implements RowMapper<Animal> {
    @Override
    public Animal mapRow(ResultSet rs, int rowNum) throws SQLException {
        Animal animal = new Animal();
        animal.setIdPet(rs.getInt("id"));
        animal.setNomePet(rs.getString("nome"));
        animal.setRacaPet(rs.getString("raca"));
        animal.setPesoPet(rs.getDouble("peso"));
        animal.setCpfDonoPet(rs.getString("fk_cliente_cpf"));
        animal.setTipoPet(rs.getString("fk_tipo"));
        
        return animal;
    }
}
