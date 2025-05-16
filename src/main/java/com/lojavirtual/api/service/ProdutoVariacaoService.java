package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.ProdutoVariacaoRequestDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoResponseDTO;
import com.lojavirtual.api.exception.VariacaoNaoEncontradaException;
import com.lojavirtual.api.mapper.ProdutoVariacaoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoVariacaoService {
    private final ProdutoVariacaoRepository variacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final CorRepository corRepository;
    private final ProdutoVariacaoMapper variacaoMapper;

    public ProdutoVariacaoResponseDTO criarVariacao(ProdutoVariacaoRequestDTO dto) {
        ProdutoVariacao variacao = variacaoMapper.toEntity(dto);

        // Resolve relacionamentos
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new VariacaoNaoEncontradaException("Produto não encontrado: " + dto.getIdProduto()));
        variacao.setProduto(produto);

        if (dto.getIdCor() != null) {
            Cor cor = corRepository.findById(dto.getIdCor())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Cor não encontrada: " + dto.getIdCor()));
            variacao.setCor(cor);
        }

        ProdutoVariacao salva = variacaoRepository.save(variacao);
        return variacaoMapper.toResponseDTO(salva);
    }

    public List<ProdutoVariacaoResponseDTO> listarTodas() {
        return variacaoRepository.findAll()
                .stream()
                .map(variacaoMapper::toResponseDTO)
                .toList();
    }

    public List<ProdutoVariacaoResponseDTO> buscarPorProduto(Long produtoId) {
        return variacaoRepository.findByProdutoId(produtoId)
                .stream()
                .map(variacaoMapper::toResponseDTO)
                .toList();
    }

    public ProdutoVariacaoResponseDTO buscarPorId(Long id) {
        ProdutoVariacao variacao = variacaoRepository.findById(id)
                .orElseThrow(() -> new VariacaoNaoEncontradaException(id));
        return variacaoMapper.toResponseDTO(variacao);
    }

    public ProdutoVariacaoResponseDTO atualizarVariacao(Long id, ProdutoVariacaoRequestDTO dto) {
        ProdutoVariacao variacao = variacaoRepository.findById(id)
                .orElseThrow(() -> new VariacaoNaoEncontradaException(id));

        // Atualiza relacionamentos se necessário
        if (dto.getIdProduto() != null) {
            Produto produto = produtoRepository.findById(dto.getIdProduto())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Produto não encontrado: " + dto.getIdProduto()));
            variacao.setProduto(produto);
        }

        if (dto.getIdCor() != null) {
            Cor cor = corRepository.findById(dto.getIdCor())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Cor não encontrada: " + dto.getIdCor()));
            variacao.setCor(cor);
        }

        variacaoMapper.updateEntityFromDTO(dto, variacao);
        ProdutoVariacao atualizada = variacaoRepository.save(variacao);
        return variacaoMapper.toResponseDTO(atualizada);
    }
}