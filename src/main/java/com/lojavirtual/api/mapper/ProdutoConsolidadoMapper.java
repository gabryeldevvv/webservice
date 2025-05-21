package com.lojavirtual.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojavirtual.api.dto.CorRequestDTO;
import com.lojavirtual.api.dto.CorResponseDTO;
import com.lojavirtual.api.dto.ImagemDTO;
import com.lojavirtual.api.dto.ProdutoConsolidadoDTO;
import com.lojavirtual.api.model.Cor;
import com.lojavirtual.api.model.ProdutoConsolidadoView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoConsolidadoMapper {
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    public ProdutoConsolidadoMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ProdutoConsolidadoDTO toResponseDTO(ProdutoConsolidadoView consolidado) {
        try {
            List<ImagemDTO> imagens = objectMapper.readValue(
                    consolidado.getImagensJson(),
                    new TypeReference<List<ImagemDTO>>() {}
            );

            return ProdutoConsolidadoDTO.builder()
                    .id(consolidado.getId())
                    .nome(consolidado.getNome())
                    .url(consolidado.getUrl())
                    .categoriasSecundarias(consolidado.getCategoriasSecundarias())
                    .preco(consolidado.getPreco())
                    .marcaNome(consolidado.getMarcaNome())
                    .corNome(consolidado.getCorNome())
                    .categoriaNome(consolidado.getCategoriaNome())
                    .imagens(imagens)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse images JSON", e);
        }
    }
}