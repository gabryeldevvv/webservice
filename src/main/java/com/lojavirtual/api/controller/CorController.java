package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.CorRequestDTO;
import com.lojavirtual.api.dto.CorResponseDTO;
import com.lojavirtual.api.service.CorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cores")
@RequiredArgsConstructor
public class CorController {
    private final CorService corService;

    @PostMapping
    public ResponseEntity<CorResponseDTO> criar(@Valid @RequestBody CorRequestDTO dto) {
        CorResponseDTO criada = corService.criarCor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<CorResponseDTO>> listar(
            @RequestParam(value = "search", required = false) String search
    ) {
        List<CorResponseDTO> lista =
                (search != null && !search.isBlank())
                        ? corService.buscarPorNomeParcial(search)
                        : corService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(corService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CorResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CorRequestDTO dto
    ) {
        return ResponseEntity.ok(corService.atualizarCor(id, dto));
    }
}