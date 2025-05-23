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
                .ativa(dto.getAtiva() != null ? dto.getAtiva() : true)
                .build();
    }

    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        CategoriaResponseDTO.CategoriaResponseDTOBuilder builder = CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .ativa(categoria.isAtiva());

        // Preenche idPai e nomePai se existir
        if (categoria.getPai() != null) {
            builder.idPai(categoria.getPai().getId())
                    .nomePai(categoria.getPai().getNome());
        }

        return builder.build();
    }

    public void updateEntityFromDTO(CategoriaRequestDTO dto, Categoria categoria) {
        if (dto.getNome() != null) {
            categoria.setNome(dto.getNome());
        }
        if (dto.getAtiva() != null) {
            categoria.setAtiva(dto.getAtiva());
        }
        // Relação de pai será tratada no service
    }
}
