package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecimento {

    Long idCompra;
    Long quantItens;
    Double valorTotal;
    String fornecedorCnpj;
    String cdInsumo;
}