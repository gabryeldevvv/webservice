package com.lojavirtual.api.exception;

public class MarcaNaoEncontradaException extends RuntimeException {
    public MarcaNaoEncontradaException(Long id) {
        super("Marca com ID " + id + " não encontrada.");
    }
}
