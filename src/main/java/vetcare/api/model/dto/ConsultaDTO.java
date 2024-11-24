package vetcare.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaDTO {
    private LocalDate data;
    private LocalTime horario;
    private String nomeAnimal;
    private String raca;
    private String nomeCliente;
    private String contatoCliente;
}
