package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.AtendimentosFaturas;
import vetcare.api.repository.entities.AtendimentosFaturasRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentosFaturasService {

    private final AtendimentosFaturasRepository atendimentosFaturasRepository;

    public AtendimentosFaturas getAtendimentoFaturaByItemFaturaId(Long idItemFatura) {
        return atendimentosFaturasRepository.findByItemFaturaId(idItemFatura);
    }

    public List<AtendimentosFaturas> getAllAtendimentosFaturas() {
        return atendimentosFaturasRepository.findAll();
    }

    public int addAtendimentoFatura(AtendimentosFaturas atendimentosFaturas) {
        return atendimentosFaturasRepository.save(atendimentosFaturas);
    }

    public int updateAtendimentoFatura(AtendimentosFaturas atendimentosFaturas) {
        return atendimentosFaturasRepository.update(atendimentosFaturas);
    }

    public int deleteAtendimentoFatura(Long idItemFatura) {
        return atendimentosFaturasRepository.deleteByItemFaturaId(idItemFatura);
    }
}
