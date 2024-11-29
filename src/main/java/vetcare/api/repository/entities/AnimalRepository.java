package vetcare.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.model.dto.AnimalClienteDTO;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.VacinaPet;
import vetcare.api.repository.mapper.dto.AnimalClienteDTORowMapper;
import vetcare.api.repository.mapper.entities.AnimalRowMapper;
import vetcare.api.repository.mapper.entities.VacinaPetRowMapper;

import java.util.List;

@Repository
public class AnimalRepository {
    private final JdbcTemplate jdbcTemplate;

    public AnimalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    * METÓDOS DE CRUD: CREATE, READ, UPDATE AND DELETE !
    * */

    // CREATE
    public int save(Animal animal) {
        String sql = "INSERT INTO Animal (id, nome, raca, peso, fk_cliente_cpf, fk_tipo) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                animal.getIdPet(),
                animal.getNomePet(),
                animal.getRacaPet(),
                animal.getPesoPet(),
                animal.getCpfDonoPet(),
                animal.getTipoPet()
        );
    }

    // READ
    public Animal findById(Long id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AnimalRowMapper(), id);
    }

    public List<Animal> findByNameContaining(String nomePet) {
        String sql = "SELECT * FROM Animal WHERE nome ILIKE ?";
        String namePattern = "%" + nomePet + "%"; // Adiciona os curingas para busca parcial
        return jdbcTemplate.query(sql, new AnimalRowMapper(), namePattern);
    }

    public AnimalClienteDTO findAnimalClienteById(Integer id) {
        String sql = """
        SELECT 
            a.id,
            a.nome,
            a.raca,
            a.peso,
            c.cpf,
            c.nome AS nomeDono,
            c.endereco,
            c.contato
        FROM 
            Animal a
        JOIN 
            Cliente c ON a.fk_cliente_cpf = c.cpf
        WHERE 
            a.id = ?
    """;

        return jdbcTemplate.queryForObject(sql, new AnimalClienteDTORowMapper(), id);
    }

    public List<Animal> findAll(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;

        String sql = "SELECT * FROM Animal LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, new AnimalRowMapper(), pageSize, offset);
    }

    // UPDATE
    public int update(Animal animal) {
        String sql = "UPDATE Animal SET nome = ?, raca = ?, peso = ?, fk_cliente_cpf = ?, fk_tipo = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                animal.getNomePet(),
                animal.getRacaPet(),
                animal.getPesoPet(),
                animal.getCpfDonoPet(),
                animal.getTipoPet(),
                animal.getIdPet()
        );
    }

    // DELETE
    public int deleteById(Integer id) {
        String sqla = "DELETE FROM AtendidoEm WHERE fk_Animal_id = ?";
        String sql = "DELETE FROM Animal WHERE id = ?";
        jdbcTemplate.update(sqla, id);
        return jdbcTemplate.update(sql, id);
    }

    public List<VacinaPet> findVacinasByAnimalId(Integer animalId) {
        String sql = """
            SELECT
            tv.nome,
            vp.fk_Atendimento_id,
            a.data
        FROM
            TipoVacina tv
        LEFT JOIN
            VacinaPet vp ON tv.nome = vp.fk_idVacina
        LEFT JOIN
            Atendimento a ON vp.fk_Atendimento_id = a.id
        LEFT JOIN
            AtendidoEm ae ON ae.fk_Atendimento_id = a.id
        WHERE
            ae.fk_Animal_id = ?;
        """;

        return jdbcTemplate.query(sql, new VacinaPetRowMapper(), animalId);
    }

    public List<VacinaPet> findVacinasPendentesByAnimalId(Integer animalId) {
        String sql = """
        SELECT
            tv.nome,
            vp.fk_Atendimento_id,
            a.data
        FROM
            TipoVacina tv
        LEFT JOIN
            VacinaPet vp ON tv.nome = vp.fk_idVacina
        LEFT JOIN
            Atendimento a ON vp.fk_Atendimento_id = a.id
        LEFT JOIN
            AtendidoEm ae ON ae.fk_Atendimento_id = a.id
        WHERE
            ae.fk_Animal_id = ?
            AND a.data <= CURRENT_DATE - INTERVAL '1 year';
    """;

        return jdbcTemplate.query(sql, new VacinaPetRowMapper(), animalId);

    }

}