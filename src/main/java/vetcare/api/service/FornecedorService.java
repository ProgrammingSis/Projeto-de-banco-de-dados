package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.Fornecedor;
import vetcare.api.repository.entities.FornecedorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public Fornecedor getFornecedorById(String cnpj) {
        return fornecedorRepository.findById(cnpj);
    }

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public int addFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public int updateFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.update(fornecedor);
    }

    public int deleteFornecedor(String cnpj) {
        return fornecedorRepository.deleteById(cnpj);
    }
}
