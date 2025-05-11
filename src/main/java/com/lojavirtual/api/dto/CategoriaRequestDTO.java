package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaRequestDTO {

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        private String nome;

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
        private String descricao;

        private Boolean ativa; // Opcional para permitir padrão true no mapper
}
