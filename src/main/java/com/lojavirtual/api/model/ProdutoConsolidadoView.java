package com.lojavirtual.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "produto_consolidado_view")
public class ProdutoConsolidadoView {

    @Id
    @Column(name = "id_variacao")
    private Long id;

    @Column(name = "nome_variacao")
    private String nome;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "url")
    private String url;

    @Column(name = "categorias_secundarias")
    private String categoriasSecundarias;

    @Column(name = "marca_nome")
    private String marcaNome;

    @Column(name = "cor_nome")
    private String corNome;

    @Column(name = "categoria_nome")
    private String categoriaNome;

    @Column(name = "imagens")
    private String imagensJson;
}