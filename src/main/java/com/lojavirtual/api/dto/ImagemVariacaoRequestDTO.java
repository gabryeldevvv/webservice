package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagemVariacaoRequestDTO {

    @NotNull(message = "ID do produto é obrigatório")
    private Long id;

    @NotBlank(message = "URL da imagem é obrigatória")
    @Size(max = 255, message = "URL deve ter até 255 caracteres")
    private String urlImagem;

    @NotNull(message = "Ordem é obrigatória")
    @Min(value = 1, message = "Ordem deve ser no mínimo 1")
    private Integer ordem;

    private Boolean principal;
}