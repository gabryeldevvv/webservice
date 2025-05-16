package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.TamanhoRequestDTO;
import com.lojavirtual.api.dto.TamanhoResponseDTO;
import com.lojavirtual.api.service.TamanhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tamanhos")
@RequiredArgsConstructor
public class TamanhoController {
    private final TamanhoService tamanhoService;

    @PostMapping
    public ResponseEntity<TamanhoResponseDTO> criar(@Valid @RequestBody TamanhoRequestDTO dto) {
        TamanhoResponseDTO criado = tamanhoService.criarTamanho(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<TamanhoResponseDTO>> listar(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "tipo", required = false) String tipo
    ) {
        List<TamanhoResponseDTO> lista = tamanhoService.buscarPorFiltro(search, tipo);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TamanhoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tamanhoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TamanhoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TamanhoRequestDTO dto
    ) {
        return ResponseEntity.ok(tamanhoService.atualizarTamanho(id, dto));
    }
}