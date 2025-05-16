package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.EnderecoRequestDTO;
import com.lojavirtual.api.dto.EnderecoResponseDTO;
import com.lojavirtual.api.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criar(@Valid @RequestBody EnderecoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.criarEndereco(dto));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar(
            @RequestParam(value = "clienteId", required = false) Long clienteId,
            @RequestParam(value = "busca", required = false) String busca
    ) {
        return ResponseEntity.ok(enderecoService.listarEnderecos(clienteId, busca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EnderecoRequestDTO dto
    ) {
        return ResponseEntity.ok(enderecoService.atualizarEndereco(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        enderecoService.excluirEndereco(id);
        return ResponseEntity.noContent().build();
    }
}