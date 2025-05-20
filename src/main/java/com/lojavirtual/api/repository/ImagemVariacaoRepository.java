package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.ImagemVariacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagemVariacaoRepository extends JpaRepository<ImagemVariacao, Long> {
    List<ImagemVariacao> findByVariacaoId(Long id);
}