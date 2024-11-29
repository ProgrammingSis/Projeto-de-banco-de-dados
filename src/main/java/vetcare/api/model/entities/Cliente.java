package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    public String cpfCliente;
    public String nomeCliente;
    public String endCliente;
    public String contatoCliente;
}