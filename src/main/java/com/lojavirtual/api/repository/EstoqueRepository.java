package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    boolean existsByProdutoVariacaoIdAndTamanhoId(Long produtoVariacaoId, Long tamanhoId);
    List<Estoque> findByProdutoVariacaoId(Long produtoVariacaoId);
    List<Estoque> findByTamanhoId(Long tamanhoId);
    Optional<Estoque> findByProdutoVariacaoIdAndTamanhoId(Long produtoVariacaoId, Long tamanhoId);
}