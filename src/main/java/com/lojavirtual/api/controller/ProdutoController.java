package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.ProdutoRequestDTO;
import com.lojavirtual.api.dto.ProdutoResponseDTO;
import com.lojavirtual.api.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        logger.info("[CONTROLLER] Requisição para criação de produto recebida.");
        try {
            ProdutoResponseDTO response = produtoService.criarProduto(dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Erro ao salvar produto", e);
            return ResponseEntity.badRequest().body("Erro ao salvar produto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar produto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @Valid @RequestBody ProdutoRequestDTO dto) {
        try {
            ProdutoResponseDTO response = produtoService.atualizarProduto(id, dto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
