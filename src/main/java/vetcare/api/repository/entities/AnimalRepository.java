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
        String sql = "SELECT * FROM Animal WHERE name LIKE ?";
        String namePattern = "%" + nomePet + "%"; // Adiciona os curingas para busca parcial
        return jdbcTemplate.query(sql, new AnimalRowMapper(), namePattern);
    }

    public AnimalClienteDTO findAnimalClienteById(Long id) {
        String sql = """
        SELECT 
            a.id AS idPet,
            a.nome AS nomePet,
            a.raca AS racaPet,
            a.peso AS pesoPet,
            c.cpf AS cpfDonoPet,
            c.nome AS nomeCliente,
            c.endereco AS endCliente,
            c.contato AS contatoCliente
        FROM 
            Animal a
        JOIN 
            Cliente c ON a.cpfDono = c.cpf
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
        String sql = "UPDATE Animal SET nome = ?, raca = ?, peso = ?, fk_cliente_cpf = ?, fk_tipo = ?, WHERE id = ?";
        return jdbcTemplate.update(sql,
                animal.getNomePet(),
                animal.getRacaPet(),
                animal.getPesoPet(),
                animal.getCpfDonoPet(),
                animal.getIdPet(),
                animal.getTipoPet()
        );
    }

    // DELETE
    public int deleteById(Long id) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<VacinaPet> findVacinasByAnimalId(Long animalId) {
        String sql = """
            SELECT 
                vp.fk_idVacina AS idVacina,
                vp.fk_Atendimento_id AS idAtendimento,
                a.data AS dataVacina,
                DATE_ADD(a.data, INTERVAL 1 YEAR) AS dataReforco
            FROM 
                VacinaPet vp
            JOIN 
                Atendimento a ON vp.fk_Atendimento_id = a.id
            JOIN 
                AtendidoEm ae ON ae.fk_Atendimento_id = a.id
            WHERE 
                ae.fk_Animal_id = ?;
        """;

        return jdbcTemplate.query(sql, new VacinaPetRowMapper(), animalId);
    }

    public List<VacinaPet> findVacinasPendentesByAnimalId(Long animalId) {
        String sql = """
            SELECT 
                tv.nome AS vacinaNome,
                vp.fk_Atendimento_id AS idAtendimento,
                a.data AS dataVacina,
                DATE_ADD(a.data, INTERVAL 1 YEAR) AS dataReforco
            FROM 
                TipoVacina tv
            LEFT JOIN 
                VacinaPet vp ON tv.id = vp.fk_idVacina
            LEFT JOIN 
                Atendimento a ON vp.fk_Atendimento_id = a.id
            LEFT JOIN 
                AtendidoEm ae ON ae.fk_Atendimento_id = a.id
            WHERE 
                ae.fk_Animal_id = ? AND vp.fk_Atendimento_id IS NULL;
        """;

        return jdbcTemplate.query(sql, new VacinaPetRowMapper(), animalId);
    }
}