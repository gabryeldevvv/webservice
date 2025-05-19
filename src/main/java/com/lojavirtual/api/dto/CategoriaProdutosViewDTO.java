package com.lojavirtual.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class CategoriaProdutosViewDTO {
    private Long id;
    private String nome;
    private List<ProdutoConsolidadoDTO> produtos;
}