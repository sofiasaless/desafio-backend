package com.sofiasaless.desafiobackend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    @Positive(message = "Insiria um valor maior que zero!")
    @NotNull(message = "Campo valor nao pode ser nulo")
    private double valor;

    @Positive(message = "Insiria um id válido!")
    @NotNull(message = "Campo pagadorId não pode ser nulo")
    private Long pagadorId;
    
    @Positive(message = "Insiria um id válido!")
    @NotNull(message = "Campo pagadorId não pode ser nulo")
    private Long beneficiarioId;

}
