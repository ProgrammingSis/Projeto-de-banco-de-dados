package vetcare.api.repository.entities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vetcare.api.model.entities.Insumo;
import vetcare.api.repository.mapper.entities.InsumoRowMapper;

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
        String sql = "INSERT INTO Insumo (nome, preco, quantidade, fk_tipo, data) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                insumo.getNomeInsumo(),
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

    public List<Insumo> findInsumoByNameContaining(String nome) {
        String sql = "SELECT * FROM Insumo WHERE nome ILIKE ?";
        String namePattern = "%" + nome + "%"; // Adiciona os curingas para busca parcial
        return jdbcTemplate.query(sql, new InsumoRowMapper(), namePattern);
    }

    public List<Insumo> findAll() {
        String sql = "SELECT * FROM Insumo";
        return jdbcTemplate.query(sql, new InsumoRowMapper());
    }

    // UPDATE
    public int update(Insumo insumo) {
        String sql = "UPDATE Insumo SET nome = ?, preco = ?, quantidade = ?, fk_tipo = ?, data = ?  WHERE ean = ?";
        return jdbcTemplate.update(sql,
                insumo.getNomeInsumo(),
                insumo.getValorInsumo(),
                insumo.getQuantInsumo(),
                insumo.getTipoInsumo(),
                insumo.getDate(),
                insumo.getCdInsumo()
        );
    }

    // DELETE
    public int deleteByCd(String ean) {
        String sqla = "DELETE FROM Fornecimento WHERE fk_Insumo_ean = ?";
        String sqlItemFatura = "DELETE FROM Item_Fatura WHERE fk_Insumo_ean = ?";
        String sql = "DELETE FROM Insumo WHERE ean = ?";
        jdbcTemplate.update(sqla, ean);
        jdbcTemplate.update(sqlItemFatura, ean);
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
