package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ImagemProdutoResponseDTO {
    private Long id;
    private Long produtoId;
    private String produtoNome;
    private String urlImagem;
    private Integer ordem;
    private Boolean principal;
}