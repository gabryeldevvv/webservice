package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TamanhoRequestDTO {

    @NotBlank(message = "A etiqueta é obrigatória")
    @Size(max = 10, message = "A etiqueta deve ter no máximo 10 caracteres")
    private String etiqueta;

    @NotBlank(message = "O tipo é obrigatório")
    @Size(max = 20, message = "O tipo deve ter no máximo 20 caracteres")
    private String tipo;
}