package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.EstoqueRequestDTO;
import com.lojavirtual.api.dto.EstoqueResponseDTO;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.EstoqueMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoVariacaoRepository produtoVariacaoRepository;
    private final TamanhoRepository tamanhoRepository;
    private final EstoqueMapper estoqueMapper;

    public EstoqueResponseDTO criarEstoque(EstoqueRequestDTO dto) {
        // Resolve relacionamentos
        ProdutoVariacao variacao = produtoVariacaoRepository.findById(dto.getVariacaoId())
                .orElseThrow(() -> new VariacaoNaoEncontradaException(dto.getVariacaoId()));
        Tamanho tamanho = tamanhoRepository.findById(dto.getTamanhoId())
                .orElseThrow(() -> new TamanhoNaoEncontradoException(dto.getTamanhoId()));

        // Verifica se combinação já existe
        if (estoqueRepository.existsByProdutoVariacaoIdAndTamanhoId(variacao.getId(), tamanho.getId())) {
            throw new EstoqueExistenteException(variacao.getId(), tamanho.getId());
        }

        Estoque estoque = Estoque.builder()
                .produtoVariacao(variacao)
                .tamanho(tamanho)
                .build();

        return estoqueMapper.toResponseDTO(estoqueRepository.save(estoque));
    }

    public List<EstoqueResponseDTO> listarTodos(Long variacaoId, Long tamanhoId) {
        if (variacaoId != null) {
            return estoqueRepository.findByProdutoVariacaoId(variacaoId)
                    .stream()
                    .map(estoqueMapper::toResponseDTO)
                    .toList();
        }
        if (tamanhoId != null) {
            return estoqueRepository.findByTamanhoId(tamanhoId)
                    .stream()
                    .map(estoqueMapper::toResponseDTO)
                    .toList();
        }
        return estoqueRepository.findAll()
                .stream()
                .map(estoqueMapper::toResponseDTO)
                .toList();
    }

    public EstoqueResponseDTO buscarPorId(Long id) {
        return estoqueRepository.findById(id)
                .map(estoqueMapper::toResponseDTO)
                .orElseThrow(() -> new EstoqueNaoEncontradoException(id));
    }

    public void excluirEstoque(Long id) {
        if (!estoqueRepository.existsById(id)) {
            throw new EstoqueNaoEncontradoException(id);
        }
        estoqueRepository.deleteById(id);
    }
}