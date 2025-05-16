package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.TamanhoRequestDTO;
import com.lojavirtual.api.dto.TamanhoResponseDTO;
import com.lojavirtual.api.exception.TamanhoNaoEncontradoException;
import com.lojavirtual.api.mapper.TamanhoMapper;
import com.lojavirtual.api.model.Tamanho;
import com.lojavirtual.api.repository.TamanhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TamanhoService {
    private final TamanhoRepository tamanhoRepository;
    private final TamanhoMapper tamanhoMapper;

    public TamanhoResponseDTO criarTamanho(TamanhoRequestDTO dto) {
        Tamanho tamanho = tamanhoMapper.toEntity(dto);
        Tamanho salvo = tamanhoRepository.save(tamanho);
        return tamanhoMapper.toResponseDTO(salvo);
    }

    public List<TamanhoResponseDTO> listarTodos() {
        return tamanhoRepository.findAll()
                .stream()
                .map(tamanhoMapper::toResponseDTO)
                .toList();
    }

    public List<TamanhoResponseDTO> buscarPorFiltro(String search, String tipo) {
        if (search != null && !search.isBlank()) {
            return tamanhoRepository.findByEtiquetaContainingIgnoreCase(search)
                    .stream()
                    .map(tamanhoMapper::toResponseDTO)
                    .toList();
        } else if (tipo != null && !tipo.isBlank()) {
            return tamanhoRepository.findByTipoContainingIgnoreCase(tipo)
                    .stream()
                    .map(tamanhoMapper::toResponseDTO)
                    .toList();
        }
        return listarTodos();
    }

    public TamanhoResponseDTO buscarPorId(Long id) {
        Tamanho tamanho = tamanhoRepository.findById(id)
                .orElseThrow(() -> new TamanhoNaoEncontradoException(id));
        return tamanhoMapper.toResponseDTO(tamanho);
    }

    public TamanhoResponseDTO atualizarTamanho(Long id, TamanhoRequestDTO dto) {
        Tamanho tamanho = tamanhoRepository.findById(id)
                .orElseThrow(() -> new TamanhoNaoEncontradoException(id));
        tamanhoMapper.updateEntityFromDTO(dto, tamanho);
        Tamanho atualizado = tamanhoRepository.save(tamanho);
        return tamanhoMapper.toResponseDTO(atualizado);
    }
}