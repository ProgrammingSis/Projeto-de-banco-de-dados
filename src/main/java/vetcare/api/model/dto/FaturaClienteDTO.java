package vetcare.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaturaClienteDTO {

    Double valorTotal;
    Date data;
    String formaPagamento;
    String clienteNome;
    String clienteContato;
}
