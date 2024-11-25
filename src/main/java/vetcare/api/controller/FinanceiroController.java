package vetcare.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vetcare.api.model.entities.Fatura;
import vetcare.api.service.FaturaService;

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

    public String adicionarFatura(Fatura fatura) {
        return faturaService.addFatura(fatura);
    }

}