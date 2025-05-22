package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.VariacaoTamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VariacaoTamanhoRepository extends JpaRepository<VariacaoTamanho, Long> {

    @Query("SELECT t.etiqueta FROM VariacaoTamanho vt JOIN vt.tamanho t WHERE vt.variacao.id = :variacaoId")
    List<String> findEtiquetasByVariacaoId(Long variacaoId);
}