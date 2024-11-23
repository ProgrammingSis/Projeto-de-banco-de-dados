package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    String cnpjFornecedor;
    String contatoFornecedor;
    String endFornecedor;
    String nomeFornecedor;
}