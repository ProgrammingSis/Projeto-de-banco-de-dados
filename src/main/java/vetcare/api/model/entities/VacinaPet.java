package vetcare.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaPet {

    String idVacina;
    Integer idAtendimento;
    Date dataVacina;
}