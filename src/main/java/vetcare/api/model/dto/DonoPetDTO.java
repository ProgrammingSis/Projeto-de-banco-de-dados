package vetcare.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonoPetDTO {

    String nomePet;
    String racaPet;
    String nomeCliente;
    String contatoCliente;
}
