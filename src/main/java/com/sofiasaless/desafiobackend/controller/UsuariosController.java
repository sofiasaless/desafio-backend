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
import com.sofiasaless.desafiobackend.dto.UsuarioResponseDTO;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.model.enums.TipoUsuario;
import com.sofiasaless.desafiobackend.useCase.CriarUsuariosUseCase;
import com.sofiasaless.desafiobackend.useCase.VisualizarUsuarioUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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

    @Operation(
        summary = "Realizar busca de usuário pelo id", 
        description = "Essa função é reponsável por fazer a busca de usuários a partir de seu id passado por end-point"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDTO.class)))
        }),
        @ApiResponse(responseCode = "400", content = {
            // @Content(array = @ArraySchema(schema = @Schema(implementation = BadRequestResponseDTO.class)))
            @Content(
                schema = @Schema(implementation = BadRequestResponseDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Usuário não encontrado",
                        value = "{ \"mensagem\": \"Usuário procurado não identificado!\" }"
                    )
                }
            )
        })
    })
    @GetMapping("/visualizar/id/{id}")
    public ResponseEntity<Object> visualizarUsuario(@PathVariable long id) {
        try {
            var result = this.visualizarUsuarioUseCase.visualizarUsuario(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BadRequestResponseDTO(e.getMessage()));
        }
    }

    @Operation(
        summary = "Realizar retorno de lista de todos os usuários", 
        description = "Essa função é reponsável por retornar todos os usuários cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
        })
    })
    @GetMapping("/visualizar")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuario() {
        var result = this.visualizarUsuarioUseCase.visualizarTodosUsuarios();
        return ResponseEntity.ok().body(result);
    }

    @Operation(
        summary = "Realizar retorno de lista de todos usuários LOJISTAS", 
        description = "Essa função é reponsável por retornar todos os usuários do TipoUsuario LOJISTA"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            // @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
            @Content(
                schema = @Schema(implementation = Usuario.class),
                examples = {
                    @ExampleObject(
                        name = "Usuário lojista",
                        value = "{ \"id\": 2, \"nome\": \"Saulo Sales\", \"documentacao\": \"009.876.112-85\", " +
                                "\"email\": \"saulsal98@gmail.com\", \"senha\": \"009s!\", \"saldo\": 66.01, " +
                                "\"tipoDoUsuario\": \"LOJISTA\", \"dataDeCriacao\": \"2025-01-01T10:00:00\" }"
                    )
                }
            )
        })
    })
    @GetMapping("/visualizar/lojistas")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuariosLojistas() {
        var result = this.visualizarUsuarioUseCase.visualizarUsuariosPorTipo(TipoUsuario.LOJISTA);
        return ResponseEntity.ok().body(result);
    }

    @Operation(
        summary = "Realizar retorno de lista de todos usuários NORMAIS", 
        description = "Essa função é reponsável por retornar todos os usuários do TipoUsuario NORMAL"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
        })
    })
    @GetMapping("/visualizar/normais")
    public ResponseEntity<List<Usuario>> visualizarTodosUsuariosNormais() {
        var result = this.visualizarUsuarioUseCase.visualizarUsuariosPorTipo(TipoUsuario.NORMAL);
        return ResponseEntity.ok().body(result);
    }

}
