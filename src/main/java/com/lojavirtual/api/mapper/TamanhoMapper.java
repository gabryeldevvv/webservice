package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.TamanhoRequestDTO;
import com.lojavirtual.api.dto.TamanhoResponseDTO;
import com.lojavirtual.api.model.Tamanho;
import org.springframework.stereotype.Component;

@Component
public class TamanhoMapper {

    public Tamanho toEntity(TamanhoRequestDTO dto) {
        return Tamanho.builder()
                .etiqueta(dto.getEtiqueta())
                .tipo(dto.getTipo())
                .build();
    }

    public TamanhoResponseDTO toResponseDTO(Tamanho tamanho) {
        return TamanhoResponseDTO.builder()
                .id(tamanho.getId())
                .etiqueta(tamanho.getEtiqueta())
                .tipo(tamanho.getTipo())
                .build();
    }

    public void updateEntityFromDTO(TamanhoRequestDTO dto, Tamanho tamanho) {
        if (dto.getEtiqueta() != null) {
            tamanho.setEtiqueta(dto.getEtiqueta());
        }
        if (dto.getTipo() != null) {
            tamanho.setTipo(dto.getTipo());
        }
    }
}