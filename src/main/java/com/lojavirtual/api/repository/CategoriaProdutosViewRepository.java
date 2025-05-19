package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.CategoriaProdutosView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaProdutosViewRepository extends JpaRepository<CategoriaProdutosView, Long> {
    List<CategoriaProdutosView> findAllByOrderByNome();
}