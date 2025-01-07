package com.sofiasaless.desafiobackend.dto;

import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDTO {

    private String nome;
    private String documentacao;
    private String email;
    private double saldo;
    private TipoUsuario tipoDoUsuario;

}
