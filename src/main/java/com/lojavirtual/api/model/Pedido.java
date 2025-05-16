package com.lojavirtual.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "pedido",
        indexes = {
                @Index(name = "idx_pedido_cliente", columnList = "id_cliente"),
                @Index(name = "idx_pedido_status", columnList = "status")
        }
)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco_entrega")
    private Endereco enderecoEntrega;

    @CreationTimestamp
    private LocalDateTime dataPedido;

    // Exemplo: PENDENTE, ENVIADO, ENTREGUE, CANCELADO
    @Column(nullable = false, length = 20)
    private String status = "PENDENTE";

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(length = 50)
    private String codigoRastreio;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pagamento pagamento;
}
