package com.sofiasaless.desafiobackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestDTO {

    @Schema(example = "Usuário beneficiário não identificado!", requiredMode = RequiredMode.REQUIRED)
    private String mensagem;
}
