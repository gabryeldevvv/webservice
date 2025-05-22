package com.lojavirtual.api.controller;

import com.lojavirtual.api.service.VariacaoTamanhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variacoes/{variacaoId}/tamanhos")
@RequiredArgsConstructor
public class VariacaoTamanhoController {

    private final VariacaoTamanhoService service;

    @GetMapping("/etiquetas")
    public List<String> listarEtiquetas(@PathVariable Long variacaoId) {
        return service.buscarEtiquetasPorVariacaoId(variacaoId);
    }
}