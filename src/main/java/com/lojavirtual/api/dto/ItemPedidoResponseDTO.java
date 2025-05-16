package com.lojavirtual.api.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ItemPedidoResponseDTO {
    private Long id;
    private Long pedidoId;
    private Long produtoId;
    private String produtoNome;
    private BigDecimal tamanho;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}