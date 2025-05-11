package com.lojavirtual.api.dto;

import com.lojavirtual.api.model.Categoria;
import com.lojavirtual.api.model.Marca;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        private String nome;

        @Size(max = 2000, message = "A descrição pode ter até 2000 caracteres")
        private String descricao;

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
        private BigDecimal preco;

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço com desconto deve ser maior que zero")
        private BigDecimal precoDesconto;

        @NotBlank(message = "O SKU é obrigatório")
        @Size(max = 50, message = "O SKU deve ter no máximo 50 caracteres")
        private String sku;

        private Boolean ativo;
        private Boolean destaque;

        private Categoria categoria;
        private Marca marca;
}
