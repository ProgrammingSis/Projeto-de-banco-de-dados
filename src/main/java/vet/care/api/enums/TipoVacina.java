package vet.care.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoVacina {

    ANTIRRÁBICA(1, "Antirrábica"),
    V10(2, "V10"),
    GIÁRDIA(3, "Giárida"),
    GRIPE_CANINA(4, "Gripe Canina"),
    V3(5, "V3"),
    V4(6, "V4"),
    LEUCEMIA_FELINA(7, "Leucemia Felina");

    private Integer id;

    private String tipoVacina;
}
