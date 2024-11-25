package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.config.MailConfig;
import vetcare.api.model.dto.FaturaClienteDTO;
import vetcare.api.model.entities.Fatura;
import vetcare.api.repository.entities.FaturaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final MailConfig mailConfig;

    public Fatura getFaturaById(Long id) {
        return faturaRepository.findById(id);
    }
    public Fatura getFaturaByCpf(String cpf) {
        return faturaRepository.findByCpf(cpf);
    }

    public List<Fatura> getAllFaturas() {
        return faturaRepository.findAll();
    }

    public String addFatura(Fatura fatura) {
        faturaRepository.save(fatura);

        return notificarFatura(fatura.getIdFatura());
    }
    public String notificarFatura(Integer faturaId) {
        FaturaClienteDTO fatura = faturaRepository.enviarComprovanteFatura(faturaId);
        // Criar o texto do e-mail
        try {
        String subject = "Comprovante de Fatura - ID " + faturaId + "VetCare";
        String text = String.format("""
            Olá %s,

            Aqui está o comprovante de sua fatura:

            - Valor: R$ %.2f
            - Data: %s
            - Forma de Pagamento: %s

            Obrigado por escolher nossos serviços.

            Atenciosamente,
            Clínica VetCare
        """, fatura.getClienteNome(), fatura.getValorTotal(),
                fatura.getData(), fatura.getFormaPagamento());
        // Enviar o e-mail
        mailConfig.enviarNotificacao(fatura.getClienteContato(), subject, text);
        return "Fatura enviada com sucesso.";
        } catch (Exception e) {
            return "Erro ao enviar o comprovante: " + e.getLocalizedMessage();
        }
    }

    public int updateFatura(Fatura fatura) {
        return faturaRepository.update(fatura);
    }

    public int deleteFatura(Long id) {
        return faturaRepository.deleteById(id);
    }
}
