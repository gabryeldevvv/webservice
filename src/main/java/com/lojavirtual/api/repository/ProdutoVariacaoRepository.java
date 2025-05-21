package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Produto;
import com.lojavirtual.api.model.ProdutoVariacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoVariacaoRepository extends JpaRepository<ProdutoVariacao, Long> {
    List<ProdutoVariacao> findByProdutoId(Long produtoId);
    Optional<ProdutoVariacao> findByUrl(String url);
}