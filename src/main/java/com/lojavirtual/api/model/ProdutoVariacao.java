package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "produto_variacao",
        indexes = @Index(name = "idx_produto_variacao_produto", columnList = "id_produto")
)
public class ProdutoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variacao")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cor")
    private Cor cor;

    @Column(name = "nome_variacao", nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean ativo = true;
}