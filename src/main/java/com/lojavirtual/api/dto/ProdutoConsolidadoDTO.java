package com.lojavirtual.api.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class ProdutoConsolidadoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String url;
    private String categoriasSecundarias;
    private String marcaNome;
    private String corNome;
    private String categoriaNome;
    private List<ImagemDTO> imagens;
}