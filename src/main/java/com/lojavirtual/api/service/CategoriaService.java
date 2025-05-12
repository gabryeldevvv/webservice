package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.CategoriaRequestDTO;
import com.lojavirtual.api.dto.CategoriaResponseDTO;
import com.lojavirtual.api.mapper.CategoriaMapper;
import com.lojavirtual.api.model.Categoria;
import com.lojavirtual.api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        Categoria salva = categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(salva);
    }

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponseDTO)
                .toList();
    }

    public List<CategoriaResponseDTO> buscarPorNomeParcial(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(categoriaMapper::toResponseDTO)
                .toList();
    }


    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoriaMapper.updateEntityFromDTO(dto, categoria);
        return categoriaMapper.toResponseDTO(categoriaRepository.save(categoria));
    }
}
