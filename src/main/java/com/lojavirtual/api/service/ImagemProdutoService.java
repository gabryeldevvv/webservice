package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.ImagemProdutoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagemProdutoService {

    private final ImagemProdutoRepository imagemRepository;
    private final ProdutoRepository produtoRepository;
    private final ImagemProdutoMapper imagemMapper;

    public ImagemProdutoResponseDTO criarImagem(ImagemProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(dto.getProdutoId()));

        ImagemProduto imagem = imagemMapper.toEntity(dto);
        imagem.setProduto(produto);

        // Lógica para imagem principal
        if (Boolean.TRUE.equals(dto.getPrincipal())) {
            if (imagemRepository.existsByProdutoIdAndPrincipalIsTrue(produto.getId())) {
                throw new ImagemPrincipalExistenteException(produto.getId());
            }
        }

        return imagemMapper.toResponseDTO(imagemRepository.save(imagem));
    }

    public List<ImagemProdutoResponseDTO> listarPorProduto(Long produtoId) {
        return imagemRepository.findByProdutoId(produtoId)
                .stream()
                .map(imagemMapper::toResponseDTO)
                .toList();
    }

    public ImagemProdutoResponseDTO atualizarImagem(Long id, ImagemProdutoRequestDTO dto) {
        ImagemProduto imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        // Atualiza relacionamento se necessário
        if (dto.getProdutoId() != null && !dto.getProdutoId().equals(imagem.getProduto().getId())) {
            Produto novoProduto = produtoRepository.findById(dto.getProdutoId())
                    .orElseThrow(() -> new ProdutoNaoEncontradoException(dto.getProdutoId()));
            imagem.setProduto(novoProduto);
        }

        // Lógica para atualizar imagem principal
        if (Boolean.TRUE.equals(dto.getPrincipal())) {
            imagemRepository.desmarcarPrincipais(imagem.getProduto().getId());
        }

        imagemMapper.updateEntityFromDTO(dto, imagem);
        return imagemMapper.toResponseDTO(imagemRepository.save(imagem));
    }

    public void excluirImagem(Long id) {
        ImagemProduto imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        // Se for principal, remove o status
        if (imagem.isPrincipal()) {
            imagemRepository.desmarcarPrincipais(imagem.getProduto().getId());
        }

        imagemRepository.delete(imagem);
    }

    public void definirComoPrincipal(Long id) {
        ImagemProduto imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        imagemRepository.desmarcarPrincipais(imagem.getProduto().getId());
        imagem.setPrincipal(true);
        imagemRepository.save(imagem);
    }
}