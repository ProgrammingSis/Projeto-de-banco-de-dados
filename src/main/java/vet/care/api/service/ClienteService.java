package vet.care.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.care.api.model.entities.Cliente;
import vet.care.api.repository.entities.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Gera construtor para atributos final
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente getClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public int addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public int updateCliente(Cliente cliente) {
        return clienteRepository.update(cliente);
    }

    public int deleteCliente(String cpf) {
        return clienteRepository.deleteByCpf(cpf);
    }

}
