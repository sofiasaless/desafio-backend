package com.sofiasaless.desafiobackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiasaless.desafiobackend.model.Lojista;
import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.repository.LojistaRepository;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UsuariosController {
    
    private final UsuarioRepository usuarioRepository;

    private final LojistaRepository lojistaRepository;

    @PostMapping("/cadastrar/usuario")
    public Usuario cadastrarUsuario (@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/cadastrar/lojista")
    public Lojista cadastrarLojista (@RequestBody Lojista lojista) {
        return lojistaRepository.save(lojista);
    }
    
}
