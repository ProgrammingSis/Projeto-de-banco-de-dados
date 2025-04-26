package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.Cliente;
import vetcare.api.repository.entities.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Gera construtor para atributos final
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente getClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> getAllClientes(String nome) {
        return clienteRepository.findAll(nome);
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
