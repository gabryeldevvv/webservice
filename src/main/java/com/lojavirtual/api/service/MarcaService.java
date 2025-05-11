package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.MarcaRequestDTO;
import com.lojavirtual.api.dto.MarcaResponseDTO;
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

    public MarcaResponseDTO atualizarMarca(Long id, MarcaRequestDTO dto) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        marcaMapper.updateEntityFromDTO(dto, marca); // Atualiza apenas campos não-nulos
        return marcaMapper.toResponseDTO(marcaRepository.save(marca));
    }
}
