package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Cor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CorRepository extends JpaRepository<Cor, Long> {
    List<Cor> findByNomeContainingIgnoreCase(String nome); // Mesmo padrão de query derivada
}