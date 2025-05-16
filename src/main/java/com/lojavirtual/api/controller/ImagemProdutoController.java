package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.service.ImagemProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imagens-produto")
@RequiredArgsConstructor
public class ImagemProdutoController {

    private final ImagemProdutoService imagemService;

    @PostMapping
    public ResponseEntity<ImagemProdutoResponseDTO> criar(@Valid @RequestBody ImagemProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagemService.criarImagem(dto));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<ImagemProdutoResponseDTO>> listarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(imagemService.listarPorProduto(produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImagemProdutoRequestDTO dto
    ) {
        return ResponseEntity.ok(imagemService.atualizarImagem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        imagemService.excluirImagem(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/principal")
    public ResponseEntity<Void> definirComoPrincipal(@PathVariable Long id) {
        imagemService.definirComoPrincipal(id);
        return ResponseEntity.noContent().build();
    }
}