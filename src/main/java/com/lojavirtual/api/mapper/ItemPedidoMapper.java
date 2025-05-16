package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ItemPedidoRequestDTO;
import com.lojavirtual.api.dto.ItemPedidoResponseDTO;
import com.lojavirtual.api.model.ItemPedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemPedidoMapper {

    public ItemPedido toEntity(ItemPedidoRequestDTO dto) {
        return ItemPedido.builder()
                .tamanho(dto.getTamanho())
                .quantidade(dto.getQuantidade())
                .precoUnitario(dto.getPrecoUnitario())
                .build();
    }

    public ItemPedidoResponseDTO toResponseDTO(ItemPedido item) {
        return ItemPedidoResponseDTO.builder()
                .id(item.getId())
                .pedidoId(item.getPedido().getId())
                .produtoId(item.getProduto().getId())
                .produtoNome(item.getProduto().getNome())
                .tamanho(item.getTamanho())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .subtotal(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .build();
    }

    public void updateEntityFromDTO(ItemPedidoRequestDTO dto, ItemPedido item) {
        if (dto.getTamanho() != null) item.setTamanho(dto.getTamanho());
        if (dto.getQuantidade() != null) item.setQuantidade(dto.getQuantidade());
        if (dto.getPrecoUnitario() != null) item.setPrecoUnitario(dto.getPrecoUnitario());
    }
}