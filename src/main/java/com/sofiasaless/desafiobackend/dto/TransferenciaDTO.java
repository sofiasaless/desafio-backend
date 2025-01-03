package com.sofiasaless.desafiobackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
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
    @Schema(example = "87.23", requiredMode = RequiredMode.REQUIRED)
    private double valor;

    @Positive(message = "Insiria um id válido!")
    @NotNull(message = "Campo pagadorId não pode ser nulo")
    @Schema(example = "7", requiredMode = RequiredMode.REQUIRED)
    private Long pagadorId;
    
    @Positive(message = "Insiria um id válido!")
    @NotNull(message = "Campo pagadorId não pode ser nulo")
    @Schema(example = "4", requiredMode = RequiredMode.REQUIRED)
    private Long beneficiarioId;

}
