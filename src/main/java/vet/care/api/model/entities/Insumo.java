package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insumo {

    String nomeInsumo;
    String cdInsumo;
    Double valorInsumo;
    String tipoInsumo;
    Long quantInsumo;
    Date date;
}