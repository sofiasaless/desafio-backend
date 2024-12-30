package com.sofiasaless.desafiobackend.useCase;

import org.springframework.stereotype.Service;

import com.sofiasaless.desafiobackend.model.Transferencia;
import com.sofiasaless.desafiobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferirValorUseCase {
    
    private UsuarioRepository usuarioRepository;

    public Transferencia efetuarTransferencia() {
        // validar se os usuarios passados existem e são válidos (ex: usuario tentando transferir para ele mesmo)
        
        
        // validar se o pagador tem saldo suficiente para transferir

        
        return null;
    }

}
