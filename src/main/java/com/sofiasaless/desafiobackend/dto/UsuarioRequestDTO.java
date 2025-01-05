package com.sofiasaless.desafiobackend.dto;

import org.hibernate.validator.constraints.Length;

import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @NotEmpty(message = "Preencha o campo nome")
    @Schema(example = "Sofia Sales", requiredMode = RequiredMode.REQUIRED)
    private String nome;

    @Length(min = 11, message = "Tamanho mínimo de 11 caracteres")
    @NotEmpty(message = "Preencha o campo documentação")
    @Schema(example = "001.002.003-99", requiredMode = RequiredMode.REQUIRED)
    private String documentacao;

    @NotEmpty(message = "Preencha o campo documentação")
    @Email(message = "Insira um e-mail válido")
    @Schema(example = "eddardstark@email.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @NotEmpty(message = "Preencha o campo senha")
    @Schema(example = "123457", requiredMode = RequiredMode.REQUIRED)
    private String senha;

    @Min(value = 0, message = "Insiria um saldo maior ou igual a zero!")
    @NotNull(message = "Campo saldo não pode ser nulo")
    @Schema(example = "10.50", requiredMode = RequiredMode.REQUIRED)
    private double saldo;

    @NotNull(message = "Insiria um valor para o tipo do usuário!")
    @Enumerated(EnumType.STRING)
    @Schema(examples = {"NORMAL", "LOJISTA"}, exampleClasses = TipoUsuario.class, requiredMode = RequiredMode.REQUIRED)
    private TipoUsuario tipoDoUsuario;

}
