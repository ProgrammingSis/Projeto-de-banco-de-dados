package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.dto.AtendimentoPetDTO;
import vetcare.api.model.dto.ConsultaDTO;
import vetcare.api.model.entities.Atendimento;
import vetcare.api.repository.entities.AtendimentoRepository;

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

    public List<AtendimentoPetDTO> getAllAtendimentosPet(Integer animalId) {
        return atendimentoRepository.findAllPetAtendimentos(animalId);
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

    public boolean agendarConsulta(Long idAtendimento, LocalDate data, LocalTime horario, int idAnimal, String crmvVeterinario, String tipoAtendimento){
        return atendimentoRepository.agendarConsulta(idAtendimento, data, horario, idAnimal, crmvVeterinario, tipoAtendimento);
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
