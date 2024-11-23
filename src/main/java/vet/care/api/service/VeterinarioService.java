package vet.care.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vet.care.api.model.entities.Veterinario;
import vet.care.api.repository.entities.VeterinarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Gera construtor para atributos final
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public Veterinario getVeterinarioByCrmv(String crmv) {
        return veterinarioRepository.findByCrmv(crmv);
    }

    public List<Veterinario> getAllVeterinarios() {
        return veterinarioRepository.findAll();
    }

    public int addVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    public int updateVeterinario(Veterinario veterinario) {
        return veterinarioRepository.update(veterinario);
    }

    public int deleteVeterinario(String crmv) {
        return veterinarioRepository.deleteByCrmv(crmv);
    }
}
