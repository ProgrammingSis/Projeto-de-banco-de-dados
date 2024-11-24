package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaPet {

    Long idVacina;
    Long idAtendimento;
    Date dataVacina;
    Date dataReforco;
}