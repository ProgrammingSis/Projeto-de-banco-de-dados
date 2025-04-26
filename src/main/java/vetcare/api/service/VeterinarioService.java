package vetcare.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vetcare.api.model.entities.Veterinario;
import vetcare.api.repository.entities.VeterinarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor // Gera construtor para atributos final
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public List<Veterinario> getAllVeterinarios(String nome) {
        return veterinarioRepository.findByNameContaining(nome);
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
