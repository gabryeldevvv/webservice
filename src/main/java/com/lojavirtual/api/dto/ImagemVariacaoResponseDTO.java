package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ImagemVariacaoResponseDTO {
    private Long id;
    private Long idVariacao;
    private String urlImagem;
    private Integer ordem;
    private Boolean principal;
}