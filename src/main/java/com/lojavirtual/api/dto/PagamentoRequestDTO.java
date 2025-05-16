package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequestDTO {

    @NotNull(message = "ID do pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotBlank(message = "Método de pagamento é obrigatório")
    @Size(max = 50, message = "Método de pagamento deve ter até 50 caracteres")
    private String metodoPagamento;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 50, message = "Status deve ter até 50 caracteres")
    private String status;

    @Size(max = 100, message = "Código de transação deve ter até 100 caracteres")
    private String codigoTransacao;
}