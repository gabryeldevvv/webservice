package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    List<Produto> findByDestaqueTrue();

    List<Produto> findByAtivoTrue();

    Optional<Produto> findBySku(String sku);

    boolean existsBySku(String sku);
}
