package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.Cliente;
import vet.care.api.repository.mapper.entities.ClienteRowMapper;

import java.util.List;

@Repository
public class ClienteRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf, nome, endereco, contato) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                cliente.getCpfCliente(),
                cliente.getNomeCliente(),
                cliente.getEndCliente(),
                cliente.getContatoCliente()
        );
    }

    // READ
    public Cliente findByCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteRowMapper(), cpf);
    }

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM Cliente";
        return jdbcTemplate.query(sql, new ClienteRowMapper());
    }

    // UPDATE
    public int update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, endereco = ?, contato = ? WHERE cpf = ?";
        return jdbcTemplate.update(sql,
                cliente.getNomeCliente(),
                cliente.getEndCliente(),
                cliente.getContatoCliente(),
                cliente.getCpfCliente()
        );
    }

    // DELETE
    public int deleteByCpf(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";
        return jdbcTemplate.update(sql, cpf);
    }

    public String buscarEmailClientePorAnimalId(Long animalId) {

        String sql = "SELECT c.contato FROM Cliente c " +
                "JOIN Animal a ON a.fk_Cliente_cpf = c.cpf " +
                "WHERE a.id = ?";

        return jdbcTemplate.queryForObject(sql, String.class, animalId);
    }
}
