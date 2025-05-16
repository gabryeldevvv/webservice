package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.ProdutoVariacaoRequestDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoResponseDTO;
import com.lojavirtual.api.service.ProdutoVariacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variacoes")
@RequiredArgsConstructor
public class ProdutoVariacaoController {
    private final ProdutoVariacaoService variacaoService;

    @PostMapping
    public ResponseEntity<ProdutoVariacaoResponseDTO> criar(@Valid @RequestBody ProdutoVariacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(variacaoService.criarVariacao(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoVariacaoResponseDTO>> listar(
            @RequestParam(value = "produtoId", required = false) Long produtoId
    ) {
        List<ProdutoVariacaoResponseDTO> lista =
                (produtoId != null)
                        ? variacaoService.buscarPorProduto(produtoId)
                        : variacaoService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoVariacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(variacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoVariacaoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoVariacaoRequestDTO dto
    ) {
        return ResponseEntity.ok(variacaoService.atualizarVariacao(id, dto));
    }
}