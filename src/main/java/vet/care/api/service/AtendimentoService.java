package vet.care.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.care.api.model.dto.ConsultaDTO;
import vet.care.api.model.dto.NotificacaoDTO;
import vet.care.api.model.entities.Atendimento;
import vet.care.api.repository.entities.AtendimentoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    public Atendimento getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id);
    }

    public List<Atendimento> getAllAtendimentos() {
        return atendimentoRepository.findAll();
    }

    public int addAtendimento(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public int updateAtendimento(Atendimento atendimento) {
        return atendimentoRepository.update(atendimento);
    }

    public boolean deletarConsulta(Long id) {
        return atendimentoRepository.deletarConsulta(id);
    }

    public boolean agendarConsulta(LocalDate data, LocalTime horario, int idAnimal, String crmvVeterinario, String tipoAtendimento){
        return atendimentoRepository.agendarConsulta(data, horario, idAnimal, crmvVeterinario, tipoAtendimento);
    }

    public List<ConsultaDTO> geraCalendarioVeterinario(String crmv, LocalDate dataInicio, LocalDate dataFim) {
        return atendimentoRepository.getCalendarioVeterinario(crmv, dataInicio, dataFim);
    }

    // Notificar agendamentos
    public String agendarNotificacoes() {
        try {
            if (atendimentoRepository.criarNotificacoes()) {
                return "Notificações agendadas com sucesso.";
            } else {
                return "Nenhum atendimento pendente para agendamento.";
            }
        } catch (Exception e) {
            return "Erro ao agendar notificações. Tente novamente mais tarde.";
        }
    }
}
