package com.lojavirtual.api.exception;

// CpfJaCadastradoException
public class CpfJaCadastradoException extends RuntimeException {
    public CpfJaCadastradoException(String cpf) {
        super("CPF " + cpf + " já está cadastrado");
    }
}