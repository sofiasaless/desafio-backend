package com.sofiasaless.desafiobackend.exception;

public class UsuarioPagadorInvalidoException extends RuntimeException {
    public UsuarioPagadorInvalidoException() {
        super("Usuários Lojistas não são autorizados de realizar transações!");
    }
}
