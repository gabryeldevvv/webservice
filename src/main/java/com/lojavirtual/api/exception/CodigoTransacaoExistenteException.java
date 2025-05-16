package com.lojavirtual.api.exception;

public class CodigoTransacaoExistenteException extends RuntimeException {
    public CodigoTransacaoExistenteException(String codigo) {
        super("Código de transação " + codigo + " já está em uso");
    }
}