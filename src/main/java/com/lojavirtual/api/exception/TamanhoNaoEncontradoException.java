package com.lojavirtual.api.exception;

public class TamanhoNaoEncontradoException extends RuntimeException {
    public TamanhoNaoEncontradoException(Long id) {
        super("Tamanho com ID " + id + " não encontrado");
    }
}