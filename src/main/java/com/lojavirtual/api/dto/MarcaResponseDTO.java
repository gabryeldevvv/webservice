package com.lojavirtual.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MarcaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String website;
    private boolean ativa;

}
