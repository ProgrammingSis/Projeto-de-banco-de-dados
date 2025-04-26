package vetcare.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vetcare.api.model.entities.Cliente;
import vetcare.api.model.entities.Fatura;
import vetcare.api.model.entities.Insumo;
import vetcare.api.service.ClienteService;
import vetcare.api.service.FaturaService;
import vetcare.api.service.InsumoService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FinanceiroController {

    private final FaturaService faturaService;
    private final ClienteService clienteService;
    private final InsumoService insumoService;

    public List<Fatura> buscarFaturaPorCliente(String cpf) {
        return faturaService.getFaturaByCpf(cpf);
    }

    public String adicionarFatura(Fatura fatura) {
        return faturaService.addFatura(fatura);
    }

    public List<Cliente> buscaClientePorNome(String nome){
        return clienteService.getAllClientes(nome);
    }

    public int addInsumo(Insumo insumo){
        return insumoService.addInsumo(insumo);
    }
}