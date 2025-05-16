package com.lojavirtual.api.exception;

public class ImagemNaoEncontradaException extends RuntimeException {
    public ImagemNaoEncontradaException(Long id) {
        super("Imagem com ID " + id + " não encontrada");
    }
}