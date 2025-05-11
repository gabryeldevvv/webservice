package com.lojavirtual.api.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MarcaRequestDTO {

        @NotBlank(message = "Nome da marca é obrigatório")
        @Size(max = 50)
        private String nome;

        private String descricao;

        @Size(max = 100)
        private String website;
}
