package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class TamanhoResponseDTO {
    private Long id;
    private String etiqueta;
    private String tipo;
}