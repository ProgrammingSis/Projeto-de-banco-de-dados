package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fatura {

    Double valorTotal;
    Date date;
    String formaPagamento;
    Integer idFatura;
    String clienteCpf;
}