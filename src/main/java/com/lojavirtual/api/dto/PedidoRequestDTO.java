package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {

    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    private Long enderecoEntregaId;

    @NotNull(message = "Valor total é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valorTotal;

    @Size(max = 50, message = "Código de rastreio deve ter até 50 caracteres")
    private String codigoRastreio;

    @Size(max = 1000, message = "Observações deve ter até 1000 caracteres")
    private String observacoes;
}