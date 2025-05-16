package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.CorRequestDTO;
import com.lojavirtual.api.dto.CorResponseDTO;
import com.lojavirtual.api.exception.CorNaoEncontradaException;
import com.lojavirtual.api.mapper.CorMapper;
import com.lojavirtual.api.model.Cor;
import com.lojavirtual.api.repository.CorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CorService {
    private final CorRepository corRepository;
    private final CorMapper corMapper;

    public CorResponseDTO criarCor(CorRequestDTO dto) {
        Cor cor = corMapper.toEntity(dto);
        Cor salva = corRepository.save(cor);
        return corMapper.toResponseDTO(salva);
    }

    public List<CorResponseDTO> listarTodas() {
        return corRepository.findAll()
                .stream()
                .map(corMapper::toResponseDTO)
                .toList();
    }

    public List<CorResponseDTO> buscarPorNomeParcial(String nome) {
        return corRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(corMapper::toResponseDTO)
                .toList();
    }

    public CorResponseDTO buscarPorId(Long id) {
        Cor cor = corRepository.findById(id)
                .orElseThrow(() -> new CorNaoEncontradaException(id));
        return corMapper.toResponseDTO(cor);
    }

    public CorResponseDTO atualizarCor(Long id, CorRequestDTO dto) {
        Cor cor = corRepository.findById(id)
                .orElseThrow(() -> new CorNaoEncontradaException(id));
        corMapper.updateEntityFromDTO(dto, cor);
        Cor atualizada = corRepository.save(cor);
        return corMapper.toResponseDTO(atualizada);
    }
}