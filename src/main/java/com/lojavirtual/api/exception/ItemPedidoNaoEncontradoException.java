package com.lojavirtual.api.exception;

public class ItemPedidoNaoEncontradoException extends RuntimeException {
    public ItemPedidoNaoEncontradoException(Long id) {
        super("Item de pedido com ID " + id + " não encontrado");
    }
}