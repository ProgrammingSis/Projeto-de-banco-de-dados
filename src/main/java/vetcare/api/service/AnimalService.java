package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.config.MailConfig;
import vetcare.api.model.dto.AnimalClienteDTO;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.VacinaPet;
import vetcare.api.repository.entities.AnimalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final MailConfig mailConfig;


    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAnimalPorNome(String nomePet){
        return animalRepository.findByNameContaining(nomePet);
    }

    public AnimalClienteDTO getAnimalClienteDTO(Integer id) {
        return animalRepository.findAnimalClienteById(id);
    }

    public List<Animal> getAllAnimals(int pageNumber, int pageSize) {
        return animalRepository.findAll(pageNumber, pageSize);
    }

    public List<VacinaPet> getVacinasByAnimalId(Integer animalId) {
        return animalRepository.findVacinasByAnimalId(animalId);
    }

    public int addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public int updateAnimal(Animal animal) {
        return animalRepository.update(animal);
    }

    public int deleteAnimal(Integer id) {
        return animalRepository.deleteById(id);
    }

    public boolean notificarVacinasPendentes(Integer animalId) {
        List<VacinaPet> vacinasPendentes = animalRepository.findVacinasPendentesByAnimalId(animalId);
        AnimalClienteDTO petDono = animalRepository.findAnimalClienteById(animalId);
        String email = petDono.getContatoCliente();

        if (vacinasPendentes.isEmpty()) {
            System.out.println("O animal não possui vacinas pendentes.");
            return false;
        } else {
            StringBuilder corpoMensagemVeterinario = new StringBuilder();
            corpoMensagemVeterinario.append("Olá, \n\n")
                    .append("Existem vacinas pendentes para o animal ").append(petDono.getNomePet()).append(":\n\n")
                    .append("As vacinas pendentes são as seguintes:\n");

            for (VacinaPet vacina : vacinasPendentes) {
                corpoMensagemVeterinario.append("- ").append(vacina.getIdVacina())
                        .append(" (Reforço: ").append(vacina.getIdVacina()).append(")\n");
            }

            corpoMensagemVeterinario.append("\n")
                    .append("Por favor, não deixe de comparecer à nossa Clínica e garantir a saúde do seu pet.\n\n")
                    .append("Atenciosamente,\nClínica Veterinária");

            mailConfig.enviarNotificacao(email, "Vacinas Pendentes para " + petDono.getNomePet(), corpoMensagemVeterinario.toString());
            return true;

        }
    }
}