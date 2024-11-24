package vetcare.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoAnimal {

    CAO(1, "Cão"),
    GATO(2, "Gato"),
    COELHO(3, "Coelho"),
    HAMSTER(4, "Hamster"),
    PORQUINHO_DA_INDIA(5, "Porquinho-da-Índia"),
    PAPAGAIO(6, "Papagaio"),
    CALOPSITA(7, "Calopsita"),
    TARTARUGA(8, "Tartaruga"),
    IGUANA(9, "Iguana");

    private Integer id;
    private String nome;
}
