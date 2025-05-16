package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criar(@Valid @RequestBody PagamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.criarPagamento(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarPorStatus(
            @RequestParam(value = "status", required = false) String status
    ) {
        return ResponseEntity.ok(
                status != null
                        ? pagamentoService.listarPorStatus(status)
                        : pagamentoService.listarTodos()
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PagamentoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam @NotBlank String status
    ) {
        return ResponseEntity.ok(pagamentoService.atualizarStatus(id, status));
    }
}