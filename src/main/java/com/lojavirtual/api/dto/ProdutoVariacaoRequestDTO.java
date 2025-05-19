package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVariacaoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Long id;

    private Long idCor;

    @NotBlank(message = "O nome da variação é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    private Boolean ativo;
}