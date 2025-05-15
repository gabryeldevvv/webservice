package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarcaRequestDTO {

        @NotBlank(message = "Nome da marca é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        private String nome;

        @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres")
        private String descricao;

        @Size(max = 100)
        private String website;
}
