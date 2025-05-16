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
        name = "tamanho",
        uniqueConstraints = @UniqueConstraint( // Mapeia a UNIQUE constraint combinada
                name = "uk_etiqueta_tipo",
                columnNames = {"etiqueta", "tipo"}
        )
)
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanho")
    private Long id;

    @Column(nullable = false, length = 10)
    private String etiqueta;

    @Column(nullable = false, length = 20)
    private String tipo;
}