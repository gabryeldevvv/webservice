package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.EstoqueRequestDTO;
import com.lojavirtual.api.dto.EstoqueResponseDTO;
import com.lojavirtual.api.service.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> criar(@Valid @RequestBody EstoqueRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueService.criarEstoque(dto));
    }

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> listar(
            @RequestParam(value = "variacaoId", required = false) Long variacaoId,
            @RequestParam(value = "tamanhoId", required = false) Long tamanhoId
    ) {
        return ResponseEntity.ok(estoqueService.listarTodos(variacaoId, tamanhoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        estoqueService.excluirEstoque(id);
        return ResponseEntity.noContent().build();
    }
}