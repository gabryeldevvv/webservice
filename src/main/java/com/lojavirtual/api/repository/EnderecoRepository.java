package com.lojavirtual.api.repository;

import com.lojavirtual.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByClienteId(Long clienteId);
    List<Endereco> findByCepContainingOrLogradouroContainingAllIgnoreCase(String cep, String logradouro);

    @Modifying
    @Query("UPDATE Endereco e SET e.enderecoPrincipal = false WHERE e.cliente.id = :clienteId")
    void desmarcarEnderecosPrincipais(@Param("clienteId") Long clienteId);
}