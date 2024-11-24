package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.Fatura;
import vetcare.api.repository.entities.FaturaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaturaService {

    private final FaturaRepository faturaRepository;

    public Fatura getFaturaById(Long id) {
        return faturaRepository.findById(id);
    }
    public Fatura getFaturaByCpf(String cpf) {
        return faturaRepository.findByCpf(cpf);
    }

    public List<Fatura> getAllFaturas() {
        return faturaRepository.findAll();
    }

    public int addFatura(Fatura fatura) {
        return faturaRepository.save(fatura);
    }

    public int updateFatura(Fatura fatura) {
        return faturaRepository.update(fatura);
    }

    public int deleteFatura(Long id) {
        return faturaRepository.deleteById(id);
    }
}
