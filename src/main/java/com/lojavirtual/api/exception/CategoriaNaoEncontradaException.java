package com.lojavirtual.api.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria com id " + id + " não encontrada");
    }
}

