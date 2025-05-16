package com.lojavirtual.api.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PagamentoResponseDTO {
    private Long id;
    private Long pedidoId;
    private BigDecimal valor;
    private String metodoPagamento;
    private String status;
    private LocalDateTime dataPagamento;
    private String codigoTransacao;
}