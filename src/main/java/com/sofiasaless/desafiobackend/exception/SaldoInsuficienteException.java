package com.sofiasaless.desafiobackend.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente para realizar transação!");
    }
}
