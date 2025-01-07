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
public class UsuarioTransferResponseDTO {

    @Schema(example = "7")
    private Long id;
    @Schema(example = "João")
    private String nome;
}
