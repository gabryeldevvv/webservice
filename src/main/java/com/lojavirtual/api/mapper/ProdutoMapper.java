package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ProdutoRequestDTO;
import com.lojavirtual.api.dto.ProdutoResponseDTO;
import com.lojavirtual.api.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto) {
        return Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .precoDesconto(dto.getPrecoDesconto())
                .sku(dto.getSku())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .destaque(dto.getDestaque() != null ? dto.getDestaque() : false)
                .categoria(dto.getCategoria())
                .marca(dto.getMarca())
                .build();
    }

    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        return ProdutoResponseDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .precoDesconto(produto.getPrecoDesconto())
                .sku(produto.getSku())
                .ativo(produto.isAtivo())
                .destaque(produto.isDestaque())
                .categoria(produto.getCategoria() != null
                        ? ProdutoResponseDTO.CategoriaResumoDTO.builder()
                        .id(produto.getCategoria().getId())
                        .nome(produto.getCategoria().getNome())
                        .build()
                        : null)
                .marca(produto.getMarca() != null
                        ? ProdutoResponseDTO.MarcaResumoDTO.builder()
                        .id(produto.getMarca().getId())
                        .nome(produto.getMarca().getNome())
                        .build()
                        : null)
                .build();
    }

    public void updateEntityFromDTO(ProdutoRequestDTO dto, Produto produto) {
        if (dto.getNome() != null) produto.setNome(dto.getNome());
        if (dto.getDescricao() != null) produto.setDescricao(dto.getDescricao());
        if (dto.getPreco() != null) produto.setPreco(dto.getPreco());
        if (dto.getPrecoDesconto() != null) produto.setPrecoDesconto(dto.getPrecoDesconto());
        if (dto.getSku() != null) produto.setSku(dto.getSku());
        if (dto.getAtivo() != null) produto.setAtivo(dto.getAtivo());
        if (dto.getDestaque() != null) produto.setDestaque(dto.getDestaque());
        if (dto.getCategoria() != null) produto.setCategoria(dto.getCategoria());
        if (dto.getMarca() != null) produto.setMarca(dto.getMarca());
    }
}
