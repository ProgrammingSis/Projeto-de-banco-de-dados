package vet.care.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vet.care.api.model.entities.Fatura;
import vet.care.api.service.AtendimentosFaturasService;
import vet.care.api.service.ClienteService;
import vet.care.api.service.FaturaService;
import vet.care.api.service.ItemFaturaService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FinanceiroController {

    private final FaturaService faturaService;

    public Fatura buscarFaturaById(Long id) {
        return faturaService.getFaturaById(id);
    }

    public Fatura buscarFaturaPorCliente(String cpf) {
        return faturaService.getFaturaByCpf(cpf);
    }

    public List<Fatura> listarFaturas() {
        return faturaService.getAllFaturas();
    }

    public int adicionarFatura(Fatura fatura) {
        return faturaService.addFatura(fatura);
    }

    public int atualizarFatura(Fatura fatura) {
        return faturaService.updateFatura(fatura);
    }

    public int deletarFatura(Long id) {
        return faturaService.deleteFatura(id);
    }
}