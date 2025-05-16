package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.service.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/itens-pedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemService;

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> criar(@Valid @RequestBody ItemPedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.criarItem(dto));
    }

    @GetMapping
    public ResponseEntity<List<ItemPedidoResponseDTO>> listar(
            @RequestParam(value = "pedidoId", required = false) Long pedidoId,
            @RequestParam(value = "produtoId", required = false) Long produtoId
    ) {
        return ResponseEntity.ok(itemService.listarItens(pedidoId, produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ItemPedidoRequestDTO dto
    ) {
        return ResponseEntity.ok(itemService.atualizarItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        itemService.excluirItem(id);
        return ResponseEntity.noContent().build();
    }
}