package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.MarcaRequestDTO;
import com.lojavirtual.api.dto.MarcaResponseDTO;
import com.lojavirtual.api.service.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {
    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> criar(@Valid @RequestBody MarcaRequestDTO dto) {
        return ResponseEntity.ok(marcaService.criarMarca(dto));
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listar() {
        return ResponseEntity.ok(marcaService.listarTodas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        return ResponseEntity.ok(marcaService.atualizarMarca(id, dto));
    }
}

