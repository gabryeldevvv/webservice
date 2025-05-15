package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.MarcaRequestDTO;
import com.lojavirtual.api.dto.MarcaResponseDTO;
import com.lojavirtual.api.model.Marca;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {

    public Marca toEntity(MarcaRequestDTO dto) {
        return Marca.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .website(dto.getWebsite())
                .ativa(dto.getAtiva() != null ? dto.getAtiva() : true)
                .build();
    }

    public MarcaResponseDTO toResponseDTO(Marca marca) {
        return MarcaResponseDTO.builder()
                .id(marca.getId())
                .nome(marca.getNome())
                .descricao(marca.getDescricao())
                .website(marca.getWebsite())
                .ativa(marca.isAtiva())
                .build();
    }

    public void updateEntityFromDTO(MarcaRequestDTO dto, Marca marca) {
        if (dto.getNome() != null) {
            marca.setNome(dto.getNome());
        }
        if (dto.getDescricao() != null) {
            marca.setDescricao(dto.getDescricao());
        }
        if (dto.getWebsite() != null) {
            marca.setWebsite(dto.getWebsite());
        }
        if (dto.getAtiva() != null) {
            marca.setAtiva(dto.getAtiva());
        }
    }
}