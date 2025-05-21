package com.lojavirtual.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal precoDesconto;
    private String url;
    private String sku;
    private boolean ativo;
    private boolean destaque;
    private CategoriaResumoDTO categoria;
    private MarcaResumoDTO marca;

    @Getter
    @Setter
    @Builder
    public static class CategoriaResumoDTO {
        private Long id;
        private String nome;
    }

    @Getter
    @Setter
    @Builder
    public static class MarcaResumoDTO {
        private Long id;
        private String nome;
    }
}
