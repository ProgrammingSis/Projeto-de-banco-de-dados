package vet.care.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum FormaPagamento {

    CARTAO_CREDITO(1, "Cartão de Crédito"),
    CARTAO_DEBITO(2, "Cartão de Débito"),
    DINHEIRO(3, "Dinheiro"),
    PIX(4, "Pix");

    private Integer id;
    private String nome;
}
