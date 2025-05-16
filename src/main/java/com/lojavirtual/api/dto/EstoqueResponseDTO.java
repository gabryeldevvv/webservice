package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class EstoqueResponseDTO {
    private Long id;
    private Long variacaoId;
    private String nomeVariacao;
    private Long tamanhoId;
    private String etiquetaTamanho;
    private String tipoTamanho;
}