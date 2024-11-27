package vetcare.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vetcare.api.model.entities.Cliente;
import vetcare.api.model.entities.Fatura;
import vetcare.api.service.ClienteService;
import vetcare.api.service.FaturaService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FinanceiroController {

    private final FaturaService faturaService;
    private final ClienteService clienteService;

    public Fatura buscarFaturaById(Long id) {
        return faturaService.getFaturaById(id);
    }

    public List<Fatura> buscarFaturaPorCliente(String cpf) {
        return faturaService.getFaturaByCpf(cpf);
    }

    public String adicionarFatura(Fatura fatura) {
        return faturaService.addFatura(fatura);
    }

    public List<Cliente> buscaClientePorNome(String nome){
        return clienteService.getAllClientes(nome);
    }
}