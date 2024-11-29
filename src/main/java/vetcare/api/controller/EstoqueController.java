package vetcare.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import vetcare.api.model.entities.Insumo;
import vetcare.api.service.InsumoService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstoqueController {

    private final InsumoService insumoService;

    /*
     *   PRINCIPAIS MÉTODOS DO FLUXO DE ESTOQUE
     * */

    // Método para buscar um insumo pelo código (ean)
    public Insumo buscarInsumoPorCodigo(String ean) {
        Insumo insumo = insumoService.getInsumoByCd(ean);
        if (insumo == null) {
            System.out.println("Insumo com EAN " + ean + " não encontrado.");
        }
        return insumo;
    }

    public List<Insumo> buscarInsumoPorNome(String nome) {
        List<Insumo> insumo = insumoService.getInsumoByNome(nome);
        if (insumo == null) {
            System.out.println("Insumo " + nome + " não encontrado.");
        }
        return insumo;
    }

    // Método para buscar todos os insumos
    public List<Insumo> buscarTodosInsumos() {
        List<Insumo> insumos = insumoService.getAllInsumos();
        if (insumos.isEmpty()) {
            System.out.println("Nenhum insumo encontrado.");
        }
        return insumos;
    }

    // Método para adicionar um insumo
    public void adicionarInsumo(Insumo insumo) {
        int result = insumoService.addInsumo(insumo);
        if (result > 0) {
            System.out.println("Insumo adicionado com sucesso: " + insumo);
        } else {
            System.out.println("Erro ao adicionar o insumo.");
        }
    }

    // Método para atualizar um insumo
    public void atualizarInsumo(Insumo insumo) {
        int result = insumoService.updateInsumo(insumo);
        if (result > 0) {
            System.out.println("Insumo atualizado com sucesso: " + insumo);
        } else {
            System.out.println("Erro ao atualizar o insumo.");
        }
    }

    // Método para deletar um insumo pelo código (ean)
    public void deletarInsumo(String ean) {
        int result = insumoService.deleteInsumo(ean);
        if (result > 0) {
            System.out.println("Insumo com EAN " + ean + " deletado com sucesso.");
        } else {
            System.out.println("Erro ao deletar o insumo ou insumo não encontrado.");
        }
    }

    // Método que verifica quais insumos estão próximos a vencer
    public List<Insumo> buscarTodosInsumosAVencer() {
        List<Insumo> insumos = insumoService.getInsumosProximosDeVencer();
        return insumos;
    }
}