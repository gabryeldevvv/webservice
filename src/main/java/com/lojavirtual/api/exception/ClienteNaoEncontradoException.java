// ClienteNaoEncontradoException
package com.lojavirtual.api.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(Long id) {
        super("Cliente com ID " + id + " não encontrado");
    }
}



