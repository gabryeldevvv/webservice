package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean ativa = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pai")
    private Categoria pai;

    @OneToMany(mappedBy = "pai", cascade = CascadeType.ALL)
    private List<Categoria> filhas;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
}
