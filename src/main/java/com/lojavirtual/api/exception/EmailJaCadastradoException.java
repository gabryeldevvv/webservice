package com.lojavirtual.api.exception;

// EmailJaCadastradoException
public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException(String email) {
        super("Email " + email + " já está cadastrado");
    }
}