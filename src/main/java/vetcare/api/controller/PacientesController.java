package vetcare.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vetcare.api.model.dto.AnimalClienteDTO;
import vetcare.api.model.entities.Animal;
import vetcare.api.model.entities.VacinaPet;
import vetcare.api.service.AnimalService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PacientesController {

    private final AnimalService animalService;

    /*
    *   PRINCIPAIS MÉTODOS DO FLUXO DE PACIENTES
    * */

    public Animal buscaPetPorId(Long id) {
        return animalService.getAnimalById(id);
    }

    public AnimalClienteDTO buscaPetDetalhes(Long id) {
        return animalService.getAnimalClienteDTO(id);
    }

    public List<Animal> listaPets(int pageNumber, int pageSize) {
        return animalService.getAllAnimals(pageNumber, pageSize);
    }

    public String adicionaPet(Animal animal) {
        int result = animalService.addAnimal(animal);
        return result > 0 ? "Pet adicionado com sucesso!" : "Falha ao adicionar pet.";
    }

    public String atualizaPet(Animal animal) {
        int result = animalService.updateAnimal(animal);
        return result > 0 ?  "Pet atualizado com sucesso!" : "Falha ao atualizar pet.";
    }

    public String deletaPet(Long id) {
        int result = animalService.deleteAnimal(id);
        return result > 0 ?  "Pet deletado com sucesso!" : "Falha ao deletar pet.";
    }

    public List<VacinaPet> buscarVacinasPet(Long animalId) {
        return animalService.getVacinasByAnimalId(animalId);
    }

    public void notificarVacinas(Long animalId) {
        animalService.notificarVacinasPendentes(animalId);
    }
}