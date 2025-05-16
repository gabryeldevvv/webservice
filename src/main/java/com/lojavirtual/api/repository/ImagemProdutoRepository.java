package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
    List<ImagemProduto> findByProdutoId(Long produtoId);
    boolean existsByProdutoIdAndPrincipalIsTrue(Long produtoId);
    @Modifying
    @Query("UPDATE ImagemProduto i SET i.principal = false WHERE i.produto.id = :produtoId")
    void desmarcarPrincipais(@Param("produtoId") Long produtoId);
}