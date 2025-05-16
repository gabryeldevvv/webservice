package com.lojavirtual.api.exception;

public class CorNaoEncontradaException extends RuntimeException {
    public CorNaoEncontradaException(Long id) {
        super("Cor com ID " + id + " não encontrada");
    }
}