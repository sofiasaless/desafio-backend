package com.sofiasaless.desafiobackend.dto;

import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {

    
    @Schema(example = "Sofia Sales", requiredMode = RequiredMode.REQUIRED)
    private String nome;
    @Schema(example = "001.002.003-99", requiredMode = RequiredMode.REQUIRED)
    private String documentacao;
    @Schema(example = "eddardstark@email.com", requiredMode = RequiredMode.REQUIRED)
    private String email;
    @Schema(example = "10.50", requiredMode = RequiredMode.REQUIRED)
    private double saldo;
    @Schema(examples = {"NORMAL", "LOJISTA"}, exampleClasses = TipoUsuario.class, requiredMode = RequiredMode.REQUIRED)
    private TipoUsuario tipoDoUsuario;
    
}
