package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.AtendidoEm;
import vetcare.api.repository.entities.AtendidoEmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendidoEmService {

    private final AtendidoEmRepository atendidoEmRepository;

    public AtendidoEm getConsultaByAtendimentoId(Long idAtendimento) {
        return atendidoEmRepository.findByAtendimentoId(idAtendimento);
    }

    public List<AtendidoEm> getAllConsultas() {
        return atendidoEmRepository.findAll();
    }

    public int addConsulta(AtendidoEm atendidoEm) {
        return atendidoEmRepository.save(atendidoEm);
    }

    public int updateConsulta(AtendidoEm atendidoEm) {
        return atendidoEmRepository.update(atendidoEm);
    }

    public int deleteConsulta(Long idAtendimento) {
        return atendidoEmRepository.deleteByAtendimentoId(idAtendimento);
    }
}
