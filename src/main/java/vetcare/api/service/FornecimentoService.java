package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.Fornecimento;
import vetcare.api.repository.entities.FornecimentoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecimentoService {

    private final FornecimentoRepository fornecimentoRepository;

    public Fornecimento getFornecimentoById(String fornecedorCnpj, String cdInsumo) {
        return fornecimentoRepository.findById(fornecedorCnpj, cdInsumo);
    }

    public List<Fornecimento> getAllFornecimentos() {
        return fornecimentoRepository.findAll();
    }

    public int addFornecimento(Fornecimento fornecimento) {
        return fornecimentoRepository.save(fornecimento);
    }

    public int updateFornecimento(Fornecimento fornecimento) {
        return fornecimentoRepository.update(fornecimento);
    }

    public int deleteFornecimento(Long id, String fornecedorCnpj, String cdInsumo) {
        return fornecimentoRepository.deleteById(id, fornecedorCnpj, cdInsumo);
    }
}
