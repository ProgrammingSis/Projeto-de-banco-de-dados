package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemFatura {

    Long quantItens;
    Long idItem;
    Long idFatura;
    Long cdInsumo;
}