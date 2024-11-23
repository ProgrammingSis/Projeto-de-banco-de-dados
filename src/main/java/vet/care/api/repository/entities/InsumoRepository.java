package vet.care.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vet.care.api.model.entities.Insumo;
import vet.care.api.repository.mapper.entities.InsumoRowMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
public class InsumoRepository {
    private final JdbcTemplate jdbcTemplate;

    public InsumoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * TODO: METÓDOS DE CRUD: CRIAR, LER, ATUALIZAR E DELETAR !
     * */

    // CREATE
    public int save(Insumo insumo) {
        String sql = "INSERT INTO Insumo (nome, ean, preco, quantidade, fk_tipo, date) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                insumo.getNomeInsumo(),
                insumo.getCdInsumo(),
                insumo.getValorInsumo(),
                insumo.getQuantInsumo(),
                insumo.getTipoInsumo(),
                insumo.getDate()
        );
    }

    // READ
    public Insumo findByCd(String ean) {
        String sql = "SELECT * FROM Insumo WHERE ean = ?";
        return jdbcTemplate.queryForObject(sql, new InsumoRowMapper(), ean);
    }

    public List<Insumo> findAll() {
        String sql = "SELECT * FROM Insumo";
        return jdbcTemplate.query(sql, new InsumoRowMapper());
    }

    // UPDATE
    public int update(Insumo insumo) {
        String sql = "UPDATE Insumo SET nome = ?, preco = ?, quantidade = ?, fk_tipo = ?, date = ?,  WHERE ean = ?";
        return jdbcTemplate.update(sql,
                insumo.getNomeInsumo(),
                insumo.getValorInsumo(),
                insumo.getQuantInsumo(),
                insumo.getCdInsumo(),
                insumo.getTipoInsumo(),
                insumo.getDate()
        );
    }

    // DELETE
    public int deleteByCd(String ean) {
        String sql = "DELETE FROM Insumo WHERE ean = ?";
        return jdbcTemplate.update(sql, ean);
    }

    // Método para buscar insumos próximos de vencer (dentro de 7 dias)
    public List<Insumo> findInsumosProximosDeVencer() {
        String query = """
                SELECT * 
                FROM Insumo 
                WHERE data BETWEEN ? AND ?
                """;

        LocalDate hoje = LocalDate.now();
        LocalDate limite = hoje.plusDays(7);

        return jdbcTemplate.query(query, new InsumoRowMapper(), hoje, limite);
    }

}
