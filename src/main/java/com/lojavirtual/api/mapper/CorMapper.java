package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.CorRequestDTO;
import com.lojavirtual.api.dto.CorResponseDTO;
import com.lojavirtual.api.model.Cor;
import org.springframework.stereotype.Component;

@Component
public class CorMapper {

    public Cor toEntity(CorRequestDTO dto) {
        return Cor.builder()
                .nome(dto.getNome())
                .codigoHex(dto.getCodigoHex())
                .build();
    }

    public CorResponseDTO toResponseDTO(Cor cor) {
        return CorResponseDTO.builder()
                .id(cor.getId())
                .nome(cor.getNome())
                .codigoHex(cor.getCodigoHex())
                .build();
    }

    public void updateEntityFromDTO(CorRequestDTO dto, Cor cor) {
        if (dto.getNome() != null) {
            cor.setNome(dto.getNome());
        }
        if (dto.getCodigoHex() != null) {
            cor.setCodigoHex(dto.getCodigoHex());
        }
    }
}