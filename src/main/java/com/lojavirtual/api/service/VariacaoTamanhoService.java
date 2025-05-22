package com.lojavirtual.api.service;

import com.lojavirtual.api.repository.VariacaoTamanhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VariacaoTamanhoService {

    private final VariacaoTamanhoRepository repository;

    public List<String> buscarEtiquetasPorVariacaoId(Long variacaoId) {
        return repository.findEtiquetasByVariacaoId(variacaoId);
    }
}