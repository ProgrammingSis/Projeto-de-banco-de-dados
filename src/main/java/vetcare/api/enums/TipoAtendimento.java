package vetcare.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TipoAtendimento {

    CONSULTA_DE_ROTINA(1, "Consulta de rotina"),
    VACINACAO(2, "Vacinação"),
    CASTRACAO(3, "Castração"),
    PROCEDIMENTO_ODONTOLOGICO(4, "Procedimento Odontológico"),
    EXAMES_LABORATORIAIS(5, "Exames laboratoriais"),
    EXAMES_DE_IMAGEM(6, "Exames de imagem"),
    ENFERMARIA(7, "Enfermaria"),
    DESPARASITACAO(8, "Desparasitação"),
    CIRURGIA(9, "Cirurgia"),
    ATENDIMENTO_DE_EMERGENCIA(10, "Atendimento de emergência"),
    INTERNACAO(11, "Internação"),
    COMPRA(12, "Compra");

    private Integer id;

    private String nome;
}
