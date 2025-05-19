package com.lojavirtual.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "categoria_produtos_view")
public class CategoriaProdutosView {

    @Id
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "categoria_nome")
    private String nome;

    @Column(name = "produtos", columnDefinition = "jsonb")
    private String produtosJson;
}