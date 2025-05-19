package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.EstoqueRequestDTO;
import com.lojavirtual.api.dto.EstoqueResponseDTO;
import com.lojavirtual.api.model.Estoque;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public EstoqueResponseDTO toResponseDTO(Estoque estoque) {
        return EstoqueResponseDTO.builder()
                .id(estoque.getId())
                .variacaoId(estoque.getProdutoVariacao().getId())
                .nomeVariacao(estoque.getProdutoVariacao().getNome())
                .tamanhoId(estoque.getTamanho().getId())
                .etiquetaTamanho(estoque.getTamanho().getEtiqueta())
                .tipoTamanho(estoque.getTamanho().getTipo())
                .build();
    }
}