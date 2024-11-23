package vet.care.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vet.care.api.model.dto.AnimalClienteDTO;
import vet.care.api.model.entities.Animal;
import vet.care.api.model.entities.VacinaPet;
import vet.care.api.repository.entities.AnimalRepository;
import vet.care.api.repository.entities.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ClienteRepository cliente;
    private final JavaMailSender mailSender;


    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id);
    }
    public AnimalClienteDTO getAnimalClienteDTO(Long id) {
        return animalRepository.findAnimalClienteById(id);
    }

    public List<Animal> getAllAnimals(int pageNumber, int pageSize) {
        return animalRepository.findAll(pageNumber, pageSize);
    }

    public List<VacinaPet> getVacinasByAnimalId(Long animalId) {
        return animalRepository.findVacinasByAnimalId(animalId);
    }

    public int addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public int updateAnimal(Animal animal) {
        return animalRepository.update(animal);
    }

    public int deleteAnimal(Long id) {
        return animalRepository.deleteById(id);
    }

    public void notificarVacinasPendentes(Long animalId) {
        List<VacinaPet> vacinasPendentes = animalRepository.findVacinasPendentesByAnimalId(animalId);

        if (vacinasPendentes.isEmpty()) {
            System.out.println("Não há vacinas pendentes para esse animal.");
            return;
        }

        String emailCliente = cliente.buscarEmailClientePorAnimalId(animalId);

        StringBuilder conteudoEmail = new StringBuilder("Olá,\n\n")
                .append("O seu pet possui vacinas pendentes a serem aplicadas:\n");

        for (VacinaPet vacina : vacinasPendentes) {
            conteudoEmail.append("Vacina: ").append(vacina.getIdVacina())
                    .append("\nData Vacina: ").append(vacina.getDataVacina())
                    .append("\nData de Reforço: ").append(vacina.getDataReforco())
                    .append("\n\n");
        }

        enviarEmail(emailCliente, conteudoEmail.toString());
    }

    private void enviarEmail(String destinatario, String conteudo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Vacinas Pendentes para seu Pet");
        message.setText(conteudo);

        mailSender.send(message);
        System.out.println("E-mail enviado para: " + destinatario);
    }
}