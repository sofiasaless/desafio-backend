package com.sofiasaless.desafiobackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {

    private double valor;
    private Long pagadorId;
    private Long beneficiarioId;

}
