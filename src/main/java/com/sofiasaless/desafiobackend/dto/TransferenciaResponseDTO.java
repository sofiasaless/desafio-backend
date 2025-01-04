package com.sofiasaless.desafiobackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferenciaResponseDTO {

    @Schema(example = "932")
    private Long id;

    @Schema(example = "87.23")
    private double valor;

    private UsuarioResponseDTO pagador;
    private UsuarioResponseDTO beneficiario;

}
