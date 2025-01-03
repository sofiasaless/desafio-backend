package com.sofiasaless.desafiobackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.dto.BadRequestDTO;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.useCase.CriarUsuariosUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UsuariosController {
    
    private final CriarUsuariosUseCase criarUsuariosUseCase;

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
