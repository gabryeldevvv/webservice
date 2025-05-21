package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.ProdutoConsolidadoDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoResponseDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoRequestDTO;
import com.lojavirtual.api.exception.ProdutoNaoEncontradoException;
import com.lojavirtual.api.exception.VariacaoNaoEncontradaException;
import com.lojavirtual.api.mapper.ProdutoConsolidadoMapper;
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
    private final ProdutoConsolidadoRepository produtoConsolidadoRepository;
    private final ProdutoVariacaoMapper variacaoMapper;
    private final ProdutoConsolidadoMapper produtoConsolidadoMapper;

    public ProdutoVariacaoResponseDTO criarVariacao(ProdutoVariacaoRequestDTO dto) {
        ProdutoVariacao variacao = variacaoMapper.toEntity(dto);

        // Resolve relacionamentos
        Produto produto = produtoRepository.findById(dto.getProduto().getId())
                .orElseThrow(() -> new VariacaoNaoEncontradaException("Produto não encontrado: " + dto.getProduto().getId()));
        variacao.setProduto(produto);

        if (dto.getCor().getId() != null) {
            Cor cor = corRepository.findById(dto.getCor().getId())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Cor não encontrada: " + dto.getCor().getId()));
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

    public ProdutoConsolidadoDTO buscarPorUrl(String url) {
        ProdutoConsolidadoView produtoConsolidadoView = produtoConsolidadoRepository.buscarPorUrl(url);
        return produtoConsolidadoMapper.toResponseDTO(produtoConsolidadoView);
    }

    public ProdutoVariacaoResponseDTO atualizarVariacao(Long id, ProdutoVariacaoRequestDTO dto) {
        ProdutoVariacao variacao = variacaoRepository.findById(id)
                .orElseThrow(() -> new VariacaoNaoEncontradaException(id));

        // Atualiza relacionamentos se necessário
        if (dto.getProduto().getId() != null) {
            Produto produto = produtoRepository.findById(dto.getProduto().getId())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Produto não encontrado: " + dto.getProduto().getId()));
            variacao.setProduto(produto);
        }

        if (dto.getCor().getId() != null) {
            Cor cor = corRepository.findById(dto.getCor().getId())
                    .orElseThrow(() -> new VariacaoNaoEncontradaException("Cor não encontrada: " + dto.getCor().getId()));
            variacao.setCor(cor);
        }

        variacaoMapper.updateEntityFromDTO(dto, variacao);
        ProdutoVariacao atualizada = variacaoRepository.save(variacao);
        return variacaoMapper.toResponseDTO(atualizada);
    }
}