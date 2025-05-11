package com.lojavirtual.api.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca") // Mantém o nome da coluna no banco
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 100)
    private String website;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean ativa = true;

    @OneToMany(mappedBy = "marca")
    private List<Produto> produtos;
}