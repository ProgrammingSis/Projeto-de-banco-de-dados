package vet.care.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalClienteDTO {

    Long idPet;
    String nomePet;
    String racaPet;
    Double pesoPet;
    String cpfDonoPet;
    String nomeCliente;
    String endCliente;
    String contatoCliente;
}
