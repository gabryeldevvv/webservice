package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class CorResponseDTO {
    private Long id;
    private String nome;
    private String codigoHex;
}