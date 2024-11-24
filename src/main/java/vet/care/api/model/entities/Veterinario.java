package vet.care.api.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    String crmvVet;
    String nomeVet;
    String tipoVet;
    String contato;
}