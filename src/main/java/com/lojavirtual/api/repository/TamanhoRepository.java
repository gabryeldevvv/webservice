package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {
    List<Tamanho> findByEtiquetaContainingIgnoreCase(String etiqueta);
    List<Tamanho> findByTipoContainingIgnoreCase(String tipo);
}