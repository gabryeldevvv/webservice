package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.ImagemVariacaoRequestDTO;
import com.lojavirtual.api.dto.ImagemVariacaoResponseDTO;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.ImagemVariacaoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImagemVariacaoService {

    private final ImagemVariacaoRepository imagemRepository;
    private final ImagemVariacaoMapper imagemMapper;
    private final ProdutoVariacaoRepository produtoVariacaoRepository;

    public ImagemVariacaoResponseDTO criarImagem(ImagemVariacaoRequestDTO dto) {
        ProdutoVariacao variacao = produtoVariacaoRepository.findById(dto.getId())
                .orElseThrow(() -> new ImagemNaoEncontradaException(dto.getId()));

        ImagemVariacao imagem = imagemMapper.toEntity(dto);
        imagem.setVariacao(variacao);

        return imagemMapper.toResponseDTO(imagemRepository.save(imagem));
    }

    public List<ImagemVariacaoResponseDTO> listarPorVariacao(Long produtoId) {
        return imagemRepository.findByVariacaoId(produtoId)
                .stream()
                .map(imagemMapper::toResponseDTO)
                .toList();
    }

    public ImagemVariacaoResponseDTO atualizarImagem(Long id, ImagemVariacaoRequestDTO dto) {
        ImagemVariacao imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        // Atualiza relacionamento se necessário
        if (dto.getId() != null && !dto.getId().equals(imagem.getVariacao().getId())) {
            ProdutoVariacao novoVariacao = produtoVariacaoRepository.findById(dto.getId())
                    .orElseThrow(() -> new ImagemNaoEncontradaException(dto.getId()));
            imagem.setVariacao(novoVariacao);
        }

        imagemMapper.updateEntityFromDTO(dto, imagem);
        return imagemMapper.toResponseDTO(imagemRepository.save(imagem));
    }

    public void excluirImagem(Long id) {
        ImagemVariacao imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        imagemRepository.delete(imagem);
    }

    public void definirComoPrincipal(Long id) {
        ImagemVariacao imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new ImagemNaoEncontradaException(id));

        imagemRepository.save(imagem);
    }
}