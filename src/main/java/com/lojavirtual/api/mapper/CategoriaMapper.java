package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.CategoriaRequestDTO;
import com.lojavirtual.api.dto.CategoriaResponseDTO;
import com.lojavirtual.api.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO dto) {
        return Categoria.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .ativa(dto.getAtiva() != null ? dto.getAtiva() : true)  // Valor padrão
                .build();
    }

    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .ativa(categoria.isAtiva())
                .build();
    }

    public void updateEntityFromDTO(CategoriaRequestDTO dto, Categoria categoria) {
        if (dto.getNome() != null) categoria.setNome(dto.getNome());
        if (dto.getDescricao() != null) categoria.setDescricao(dto.getDescricao());
        if (dto.getAtiva() != null) categoria.setAtiva(dto.getAtiva());
    }
}
