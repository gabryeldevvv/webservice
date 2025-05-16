package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagem_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem") // Mantém o nome da coluna no banco
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false, length = 255)
    private String urlImagem;

    @Column(nullable = false)
    private Integer ordem = 1;

    @Column(columnDefinition = "boolean default false")
    private boolean principal = false;
}