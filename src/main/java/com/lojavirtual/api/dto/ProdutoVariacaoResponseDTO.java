package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ProdutoVariacaoResponseDTO {
    private Long id;
    private String nomeVariacao;
    private Boolean ativo;
    private Long idProduto;
    private String nomeProduto;
    private Long idCor;
    private String nomeCor;
    private String codigoHexCor; // Extra do relacionamento
}