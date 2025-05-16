package com.lojavirtual.api.exception;

public class EstoqueExistenteException extends RuntimeException {
    public EstoqueExistenteException(Long variacaoId, Long tamanhoId) {
        super("Combinação variação " + variacaoId + " / tamanho " + tamanhoId + " já existe no estoque");
    }
}