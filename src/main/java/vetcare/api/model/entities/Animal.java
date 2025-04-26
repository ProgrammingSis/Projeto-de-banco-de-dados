package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor       // Gera um construtor vazio
@AllArgsConstructor      // Gera um construtor com todos os atributos
public class Animal {

    public Integer idPet;
    public String nomePet;
    public String racaPet;
    public Double pesoPet;
    public String cpfDonoPet;
    public String tipoPet;
}