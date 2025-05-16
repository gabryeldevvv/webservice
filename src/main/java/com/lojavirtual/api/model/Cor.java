package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cor")
public class Cor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cor")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String nome;

    @Column(name = "codigo_hex", length = 7)
    private String codigoHex;
}