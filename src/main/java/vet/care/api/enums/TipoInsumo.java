package vet.care.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoInsumo {

    ADMINISTRATIVO(1, "Administrativo"),
    LIMPEZA(2, "Limpeza"),
    ACESSORIOS_PARA_PETS(3, "Acessórios para Pets"),
    ALIMENTOS_PARA_PETS(4, "Alimentos para Pets"),
    HIGIENE_PARA_PETS(5, "Higiene para Pets"),
    EXAMES_E_DIAGNOSTICOS(6, "Exames e Diagnósticos"),
    MEDICOS(7, "Médicos");

    private Integer id;
    private String nome;
}