package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.MarcaRequestDTO;
import com.lojavirtual.api.dto.MarcaResponseDTO;
import com.lojavirtual.api.exception.MarcaNaoEncontradaException;
import com.lojavirtual.api.mapper.MarcaMapper;
import com.lojavirtual.api.model.Marca;
import com.lojavirtual.api.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    public MarcaResponseDTO criarMarca(MarcaRequestDTO dto) {
        Marca marca = marcaMapper.toEntity(dto);
        Marca salva = marcaRepository.save(marca);
        return marcaMapper.toResponseDTO(salva);
    }

    public List<MarcaResponseDTO> listarTodas() {
        return marcaRepository.findAll()
                .stream()
                .map(marcaMapper::toResponseDTO)
                .toList();
    }

    public List<MarcaResponseDTO> buscarPorNomeParcial(String nome) {
        return marcaRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(marcaMapper::toResponseDTO)
                .toList();
    }

    public MarcaResponseDTO buscarPorId(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new MarcaNaoEncontradaException(id));
        return marcaMapper.toResponseDTO(marca);
    }

    public MarcaResponseDTO atualizarMarca(Long id, MarcaRequestDTO dto) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new MarcaNaoEncontradaException(id));

        marcaMapper.updateEntityFromDTO(dto, marca);

        Marca atualizada = marcaRepository.save(marca);
        return marcaMapper.toResponseDTO(atualizada);
    }
}
