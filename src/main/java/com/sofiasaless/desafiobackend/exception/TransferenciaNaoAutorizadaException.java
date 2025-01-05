package com.sofiasaless.desafiobackend.exception;

public class TransferenciaNaoAutorizadaException extends RuntimeException {
    public TransferenciaNaoAutorizadaException() {
        super("Transferência não autorizada!");
    }
}
