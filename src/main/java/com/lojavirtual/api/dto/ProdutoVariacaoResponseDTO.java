package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ProdutoVariacaoResponseDTO {

    private Long id;
    private String nome;
    private Boolean ativo;
    private ProdutoResumoDTO produto;
    private String url;
    private CorResumoDTO cor;

    @Getter
    @Setter
    @Builder
    public static class ProdutoResumoDTO {
        private Long id;
        private String nome;
    }

    @Getter
    @Setter
    @Builder
    public static class CorResumoDTO {
        private Long id;
        private String nome;
        private String codigoHex;
    }
}