package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueRequestDTO {

    @NotNull(message = "ID da variação é obrigatório")
    private Long variacaoId;

    @NotNull(message = "ID do tamanho é obrigatório")
    private Long tamanhoId;
}