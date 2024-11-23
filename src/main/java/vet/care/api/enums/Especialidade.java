package vet.care.api.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Especialidade {

    CLINICA_GERAL (1, "Clínica Geral"),
    CIRUGIA_GERAL (2, "Cirurgia Geral"),
    DERMATOLOGIA (3, "Dermatologia"),
    ANESTESIOLOGIA (4, "Anestesiologia"),
    CARDIOLOGIA (5, "Cardiologia"),
    ODONTOLOGIA (6, "Odontologia"),
    ORTOPEDIA (7, "Ortopedia"),
    RADIOLOGIA (8, "Radiologia Veterinária");

    private Integer id;

    private String tipoEspecialidade;
}
