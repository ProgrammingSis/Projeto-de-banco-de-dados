package vetcare.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoPetDTO {
    String crmvVet;
    Integer idAtendimento;
    Integer idPet;
    Date date;
    String tipoAtendimento;
    Time horario;
}