package com.lojavirtual.api.exception;

// ImagemPrincipalExistenteException
public class ImagemPrincipalExistenteException extends RuntimeException {
    public ImagemPrincipalExistenteException(Long produtoId) {
        super("Produto " + produtoId + " já possui uma imagem principal");
    }
}