package com.sofiasaless.desafiobackend.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String tipo) {
        super("Usuário " + tipo + " não identificado!");
    }
}
