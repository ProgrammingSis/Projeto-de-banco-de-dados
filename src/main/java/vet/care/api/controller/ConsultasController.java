package vet.care.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.BackOff;
import vet.care.api.model.dto.ConsultaDTO;
import vet.care.api.model.entities.Atendimento;
import vet.care.api.model.entities.Veterinario;
import vet.care.api.service.AtendimentoService;
import vet.care.api.service.AtendidoEmService;
import vet.care.api.service.VeterinarioService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultasController {

    private final AtendimentoService atendimentoService;
    private final VeterinarioService veterinarioService;

    /*
     *   PRINCIPAIS MÉTODOS DO FLUXO DE CONSULTAS
     * */

    public Veterinario buscarVeterinarioPorCrmv(String crmv) {
        try {
            Veterinario veterinario = veterinarioService.getVeterinarioByCrmv(crmv);
            if (veterinario == null) {
                System.out.println("Nenhum veterinário encontrado com o CRMV: " + crmv);
            }
            return veterinario;
        } catch (Exception e) {
            System.err.println("Erro ao buscar veterinário: " + e.getMessage());
            return null;
        }
    }

    public List<Veterinario> listarTodosVeterinarios() {
        try {
            List<Veterinario> veterinarios = veterinarioService.getAllVeterinarios();
            if (veterinarios.isEmpty()) {
                System.out.println("Nenhum veterinário encontrado.");
            }
            return veterinarios;
        } catch (Exception e) {
            System.err.println("Erro ao listar veterinários: " + e.getMessage());
            return List.of();
        }
    }

    public boolean adicionarVeterinario(Veterinario veterinario) {
        try {
            int resultado = veterinarioService.addVeterinario(veterinario);
            if (resultado > 0) {
                System.out.println("Veterinário adicionado com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao adicionar veterinário.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao adicionar veterinário: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarVeterinario(Veterinario veterinario) {
        try {
            int resultado = veterinarioService.updateVeterinario(veterinario);
            if (resultado > 0) {
                System.out.println("Veterinário atualizado com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao atualizar veterinário.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar veterinário: " + e.getMessage());
            return false;
        }
    }

    public boolean removerVeterinario(String crmv) {
        try {
            int resultado = veterinarioService.deleteVeterinario(crmv);
            if (resultado > 0) {
                System.out.println("Veterinário removido com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao remover veterinário.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("Erro ao remover veterinário: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirAgendamento(Long id){
       return atendimentoService.deletarConsulta(id);
    }

    public boolean criarConsulta(LocalDate data, LocalTime horario, int idAnimal, String crmvVeterinario, String tipoAtendimento){
        return atendimentoService.agendarConsulta(data, horario, idAnimal, crmvVeterinario, tipoAtendimento);
    }

    public List<ConsultaDTO> mostrarCalendarioVeterinario(String crmv, String dataInicioStr, String dataFimStr) {
        // Converte as strings de data para LocalDate
        LocalDate dataInicio = LocalDate.parse(dataInicioStr);
        LocalDate dataFim = LocalDate.parse(dataFimStr);

        return atendimentoService.geraCalendarioVeterinario(crmv, dataInicio, dataFim);
    }

    public String notificarConsulta(){
        return atendimentoService.agendarNotificacoes();
    }
}