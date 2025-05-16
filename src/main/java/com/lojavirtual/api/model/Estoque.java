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
        name = "estoque",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_estoque_variacao_tamanho",
                columnNames = {"id_variacao", "id_tamanho"}
        ),
        indexes = {
            @Index(name = "idx_estoque_variacao", columnList = "id_variacao"),
            @Index(name = "idx_estoque_tamanho", columnList = "id_tamanho")
        }

            )
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_variacao")
    private ProdutoVariacao produtoVariacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;
}