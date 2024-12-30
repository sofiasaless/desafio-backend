package com.sofiasaless.desafiobackend.useCase;

import org.springframework.stereotype.Service;

import com.sofiasaless.desafiobackend.model.Usuario;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizarSaldoUseCase {
    
    private final UsuarioRepository usuarioRepository;

    public Usuario atualizarSaldo(Long idUsuario, double novoSaldo) {
        Usuario usuarioEmDebito = this.usuarioRepository.findById(idUsuario).get();
    
        usuarioEmDebito.setSaldo(novoSaldo);

        return this.usuarioRepository.save(usuarioEmDebito);
    }

}
