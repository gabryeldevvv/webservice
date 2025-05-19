package com.lojavirtual.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojavirtual.api.dto.CategoriaProdutosViewDTO;
import com.lojavirtual.api.dto.ProdutoConsolidadoDTO;
import com.lojavirtual.api.exception.CategoriaProdutosNaoEncontradaException;
import com.lojavirtual.api.mapper.ProdutoMapper;
import com.lojavirtual.api.model.CategoriaProdutosView;
import com.lojavirtual.api.repository.CategoriaProdutosViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaProdutosService {

    private final CategoriaProdutosViewRepository repository;
    private final ObjectMapper objectMapper;

    public List<CategoriaProdutosViewDTO> listarTodasCategoriasComProdutos() {
        List<CategoriaProdutosView> views = repository.findAllByOrderByNome();

        return views.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CategoriaProdutosViewDTO convertToDto(CategoriaProdutosView view) {
        try {
            List<ProdutoConsolidadoDTO> produtos = convertJsonToProdutos(view.getProdutosJson());

            return CategoriaProdutosViewDTO.builder()
                    .id(view.getId())
                    .nome(view.getNome())
                    .produtos(produtos)
                    .build();
        } catch (Exception e) {
            throw new CategoriaProdutosNaoEncontradaException("Erro ao converter produtos da categoria");
        }
    }

    private List<ProdutoConsolidadoDTO> convertJsonToProdutos(String produtosJson) throws Exception {
        // Primeiro converte para uma lista de objetos intermediários
        List<ProdutoConsolidadoDTO> produtos = objectMapper.readValue(
                produtosJson,
                new TypeReference<List<ProdutoConsolidadoDTO>>() {}
        );

        // Se necessário, pode aplicar transformações adicionais aqui
        return produtos;
    }
}