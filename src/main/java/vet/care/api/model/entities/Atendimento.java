package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atendimento {

    Date date;
    String tipoAtendimento;
    Long idAtendimento;
}