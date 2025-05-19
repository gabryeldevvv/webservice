package com.lojavirtual.api.controller;

import com.lojavirtual.api.dto.CategoriaProdutosViewDTO;
import com.lojavirtual.api.service.CategoriaProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaProdutosController {

    private final CategoriaProdutosService service;

    @GetMapping("/com-produtos")
    public ResponseEntity<List<CategoriaProdutosViewDTO>> listarCategoriasComProdutos() {
        List<CategoriaProdutosViewDTO> categorias = service.listarTodasCategoriasComProdutos();
        return ResponseEntity.ok(categorias);
    }
}