package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoRequestDTO {

    @NotNull(message = "ID do pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message = "ID do produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "Tamanho é obrigatório")
    @Positive(message = "Tamanho deve ser positivo")
    @Digits(integer = 3, fraction = 1, message = "Tamanho inválido (formato: 00.0)")
    private BigDecimal tamanho;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 1, message = "Quantidade mínima é 1")
    private Integer quantidade;

    @NotNull(message = "Preço unitário é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    @Digits(integer = 8, fraction = 2, message = "Preço inválido")
    private BigDecimal precoUnitario;
}