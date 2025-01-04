package com.sofiasaless.desafiobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.dto.BadRequestDTO;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.useCase.CriarUsuariosUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Cadastros", description = "Pontos relacionados ao cadastros de usuários")
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UsuariosController {
    
    private final CriarUsuariosUseCase criarUsuariosUseCase;

    @Operation(
        summary = "Realizar cadastro de novos usuários", 
        description = "Essa função é reponsável por cadastrar usuários de tipo NORMAL e LOJISTA"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
        })
    })
    @PostMapping("/cadastrar/usuario")
    public ResponseEntity<Object> cadastrarUsuario (@Valid @RequestBody Usuario usuario) {
        try {
            var result = this.criarUsuariosUseCase.criarUsuario(usuario);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequestDTO(e.getMessage()));
        }
    }

}
