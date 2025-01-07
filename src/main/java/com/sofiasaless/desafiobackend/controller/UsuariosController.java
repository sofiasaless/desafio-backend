package com.sofiasaless.desafiobackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.dto.BadRequestResponseDTO;
import com.sofiasaless.desafiobackend.dto.UsuarioRequestDTO;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.useCase.CriarUsuariosUseCase;
import com.sofiasaless.desafiobackend.useCase.VisualizarUsuarioUseCase;

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
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    
    private final CriarUsuariosUseCase criarUsuariosUseCase;

    private final VisualizarUsuarioUseCase visualizarUsuarioUseCase;

    @Operation(
        summary = "Realizar cadastro de novos usuários", 
        description = "Essa função é reponsável por cadastrar usuários de tipo NORMAL e LOJISTA"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
        })
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUsuario (@Valid @RequestBody UsuarioRequestDTO usuario) {
        try {
            var result = this.criarUsuariosUseCase.criarUsuario(usuario);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequestResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/visualizar/id/{id}")
    public ResponseEntity<Object> visualizarUsuario(@PathVariable long id) {
        try {
            var result = this.visualizarUsuarioUseCase.visualizarUsuario(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequestResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/visualizar")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuario() {
        var result = this.visualizarUsuarioUseCase.visualizarTodosUsuarios();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/visualizar/lojistas")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuariosLojistas() {
        var result = this.visualizarUsuarioUseCase.visualizarUsuariosPorTipo(TipoUsuario.LOJISTA);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/visualizar/normais")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuariosNormais() {
        var result = this.visualizarUsuarioUseCase.visualizarUsuariosPorTipo(TipoUsuario.NORMAL);
        return ResponseEntity.ok().body(result);
    }

}
