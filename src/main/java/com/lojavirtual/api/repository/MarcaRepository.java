package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNomeContainingIgnoreCase(String nome);
}