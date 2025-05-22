package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "variacao_tamanhos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariacaoTamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variacao_tamanho")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_variacao", nullable = false)
    private ProdutoVariacao variacao;

    @ManyToOne
    @JoinColumn(name = "id_tamanho", nullable = false)
    private Tamanho tamanho;
}