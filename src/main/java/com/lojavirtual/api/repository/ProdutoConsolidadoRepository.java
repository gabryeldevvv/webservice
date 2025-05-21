package com.lojavirtual.api.repository;

import com.lojavirtual.api.dto.ProdutoConsolidadoDTO;
import com.lojavirtual.api.model.ProdutoConsolidadoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoConsolidadoRepository extends JpaRepository<ProdutoConsolidadoView, Long> {

    @Query(value = "SELECT * FROM buscar_produto_por_url(:url) LIMIT 1", nativeQuery = true)
    ProdutoConsolidadoView buscarPorUrl(@Param("url") String url);
}