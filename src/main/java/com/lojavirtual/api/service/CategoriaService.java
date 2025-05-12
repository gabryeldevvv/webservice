package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.CategoriaRequestDTO;
import com.lojavirtual.api.dto.CategoriaResponseDTO;
import com.lojavirtual.api.exception.CategoriaNaoEncontradaException;
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
        // Mapeia os campos básicos
        Categoria categoria = categoriaMapper.toEntity(dto);

        // Se idPai for informado, busca e associa a categoria pai
        if (dto.getIdPai() != null) {
            Categoria pai = categoriaRepository.findById(dto.getIdPai())
                    .orElseThrow(() -> new CategoriaNaoEncontradaException(dto.getIdPai()));
            categoria.setPai(pai);
        }

        // Salva a categoria
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

    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        return categoriaMapper.toResponseDTO(categoria);
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO dto) {
        // Busca a categoria existente
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));

        // Atualiza campos básicos
        categoriaMapper.updateEntityFromDTO(dto, categoria);

        // Ajusta relação de categoria pai
        if (dto.getIdPai() != null) {
            Categoria pai = categoriaRepository.findById(dto.getIdPai())
                    .orElseThrow(() -> new CategoriaNaoEncontradaException(dto.getIdPai()));
            categoria.setPai(pai);
        } else {
            categoria.setPai(null);
        }

        Categoria atualizada = categoriaRepository.save(categoria);
        return categoriaMapper.toResponseDTO(atualizada);
    }
}
