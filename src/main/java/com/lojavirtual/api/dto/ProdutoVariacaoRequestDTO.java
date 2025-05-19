package com.lojavirtual.api.dto;

import com.lojavirtual.api.model.Cor;
import com.lojavirtual.api.model.Produto;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVariacaoRequestDTO {

    @NotBlank(message = "O nome da variação é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    private Boolean ativo;

    @NotNull(message = "O Produto é obrigatório")
    private Produto produto;

    @NotNull(message = "A Cor é obrigatória")
    private Cor cor;
}