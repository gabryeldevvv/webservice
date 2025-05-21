package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.CorRequestDTO;
import com.lojavirtual.api.dto.CorResponseDTO;
import com.lojavirtual.api.dto.ProdutoConsolidadoDTO;
import com.lojavirtual.api.model.Cor;
import com.lojavirtual.api.model.ProdutoConsolidadoView;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsolidadoMapper {

    public ProdutoConsolidadoDTO toResponseDTO(ProdutoConsolidadoView consolidado) {
        return ProdutoConsolidadoDTO.builder()
                .id(consolidado.getId())
                .nome(consolidado.getNome())
                .url(consolidado.getUrl())
                .categoriasSecundarias(consolidado.getCategoriasSecundarias())
                .preco(consolidado.getPreco())
                .marcaNome(consolidado.getMarcaNome())
                .corNome(consolidado.getCorNome())
                .categoriaNome(consolidado.getCategoriaNome())
                .imagens(consolidado.getImagensJson())
                .build();
    }
}