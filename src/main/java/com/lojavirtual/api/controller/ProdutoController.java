package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.ProdutoRequestDTO;
import com.lojavirtual.api.dto.ProdutoResponseDTO;
import com.lojavirtual.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody ProdutoRequestDTO produtoDTO) {
        logger.info("[CONTROLLER] Requisição para criação de produto recebida.");

        try {
            ProdutoResponseDTO response = produtoService.criarProduto(produtoDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Erro ao salvar produto", e);
            return ResponseEntity.badRequest().body("Erro ao salvar produto: " + e.getMessage());
        }
    }
}
