package com.lojavirtual.api.exception;

public class VariacaoNaoEncontradaException extends RuntimeException {
    public VariacaoNaoEncontradaException(Long id) {
        super("Variação com ID " + id + " não encontrada");
    }

    public VariacaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}