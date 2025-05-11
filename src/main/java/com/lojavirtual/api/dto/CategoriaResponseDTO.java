package com.lojavirtual.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private boolean ativa;

}
