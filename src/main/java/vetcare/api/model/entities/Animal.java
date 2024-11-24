package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor       // Gera um construtor vazio
@AllArgsConstructor      // Gera um construtor com todos os atributos
public class Animal {

    Integer idPet;
    String nomePet;
    String racaPet;
    Double pesoPet;
    String cpfDonoPet;
    String tipoPet;
}