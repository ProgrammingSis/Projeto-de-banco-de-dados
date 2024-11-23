package vet.care.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.care.api.model.entities.AtendidoEm;
import vet.care.api.repository.entities.AtendidoEmRepository;

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
