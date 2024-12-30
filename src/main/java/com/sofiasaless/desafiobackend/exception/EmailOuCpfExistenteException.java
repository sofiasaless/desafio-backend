package com.sofiasaless.desafiobackend.exception;

public class EmailOuCpfExistenteException extends RuntimeException {
    public EmailOuCpfExistenteException () {
        super("E-mail ou CPF/CNPJ já cadastrados!");
    }
}
