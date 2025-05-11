package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.ProdutoRequestDTO;
import com.lojavirtual.api.dto.ProdutoResponseDTO;
import com.lojavirtual.api.mapper.ProdutoMapper;
import com.lojavirtual.api.model.Produto;
import com.lojavirtual.api.repository.CategoriaRepository;
import com.lojavirtual.api.repository.MarcaRepository;
import com.lojavirtual.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);

        // Valida e carrega relacionamentos
        if (dto.getCategoria() != null) {
            produto.setCategoria(
                    categoriaRepository.findById(dto.getCategoria().getId())
                            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"))
            );
        }

        if (dto.getMarca() != null) {
            produto.setMarca(
                    marcaRepository.findById(dto.getMarca().getId())
                            .orElseThrow(() -> new RuntimeException("Marca não encontrada"))
            );
        }

        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(salvo);
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoMapper.updateEntityFromDTO(dto, produto);

        Produto salvo = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(salvo);
    }

    // Outros métodos (listar, deletar, etc.) podem ser implementados conforme necessário.
}
