package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.CategoriaRequestDTO;
import com.lojavirtual.api.dto.CategoriaResponseDTO;
import com.lojavirtual.api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@Valid @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.criarCategoria(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<CategoriaResponseDTO>> listarPorBusca(
            @RequestParam(value = "search", required = false) String search
    ) {
        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(categoriaService.buscarPorNomeParcial(search));
        }
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        CategoriaResponseDTO dto = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto
    ) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, dto));
    }
}
