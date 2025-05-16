package com.lojavirtual.api.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PedidoResponseDTO {
    private Long idPedido;
    private Long clienteId;
    private String clienteNome;
    private String enderecoEntrega;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;
    private String codigoRastreio;
    private String observacoes;
    private List<ItemPedidoResponseDTO> itens;
    private PagamentoResponseDTO pagamento;
}