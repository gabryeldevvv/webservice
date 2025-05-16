package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByPedidoId(Long pedidoId);
    List<Pagamento> findByStatus(String status);
    Optional<Pagamento> findByCodigoTransacao(String codigoTransacao);
    boolean existsByCodigoTransacao(String codigoTransacao);
}