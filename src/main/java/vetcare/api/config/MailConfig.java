package vetcare.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Autowired
    public JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarNotificacao(String toEmail, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            return "Email enviado com sucesso.";
        } catch(Exception e) {
            return "Erro ao tentar enviar email " + e.getLocalizedMessage();
        }
    }
}