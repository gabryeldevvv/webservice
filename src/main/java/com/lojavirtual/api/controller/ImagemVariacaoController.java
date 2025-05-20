package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.ImagemVariacaoRequestDTO;
import com.lojavirtual.api.dto.ImagemVariacaoResponseDTO;
import com.lojavirtual.api.service.ImagemVariacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imagens-produto")
@RequiredArgsConstructor
public class ImagemVariacaoController {

    private final ImagemVariacaoService imagemService;

    @PostMapping
    public ResponseEntity<ImagemVariacaoResponseDTO> criar(@Valid @RequestBody ImagemVariacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagemService.criarImagem(dto));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<ImagemVariacaoResponseDTO>> listarPorVariacao(@PathVariable Long produtoId) {
        return ResponseEntity.ok(imagemService.listarPorVariacao(produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagemVariacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ImagemVariacaoRequestDTO dto
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