package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.MarcaRequestDTO;
import com.lojavirtual.api.dto.MarcaResponseDTO;
import com.lojavirtual.api.service.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {
    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> criar(
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        MarcaResponseDTO criado = marcaService.criarMarca(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listar(
            @RequestParam(value = "search", required = false) String search
    ) {
        List<MarcaResponseDTO> lista =
                (search != null && !search.isBlank())
                        ? marcaService.buscarPorNomeParcial(search)
                        : marcaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        MarcaResponseDTO dto = marcaService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody MarcaRequestDTO dto
    ) {
        MarcaResponseDTO atualizado = marcaService.atualizarMarca(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}

