package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ProdutoVariacaoRequestDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoResponseDTO;
import com.lojavirtual.api.model.ProdutoVariacao;
import org.springframework.stereotype.Component;

@Component
public class ProdutoVariacaoMapper {

    public ProdutoVariacao toEntity(ProdutoVariacaoRequestDTO dto) {
        return ProdutoVariacao.builder()
                .nome(dto.getNome())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
    }

    public ProdutoVariacaoResponseDTO toResponseDTO(ProdutoVariacao variacao) {
        return ProdutoVariacaoResponseDTO.builder()
                .id(variacao.getId())
                .nome(variacao.getNome())
                .ativo(variacao.isAtivo())
                .url(variacao.getUrl())
                .produto(ProdutoVariacaoResponseDTO.ProdutoResumoDTO.builder()
                        .id(variacao.getProduto().getId())
                        .nome(variacao.getProduto().getNome())
                        .build())
                .cor(variacao.getCor() != null ? ProdutoVariacaoResponseDTO.CorResumoDTO.builder()
                        .id(variacao.getCor().getId())
                        .nome(variacao.getCor().getNome())
                        .codigoHex(variacao.getCor().getCodigoHex())
                        .build() : null)
                .build();
    }

    public void updateEntityFromDTO(ProdutoVariacaoRequestDTO dto, ProdutoVariacao variacao) {
        if (dto.getNome() != null) {
            variacao.setNome(dto.getNome());
        }
        if (dto.getAtivo() != null) {
            variacao.setAtivo(dto.getAtivo());
        }
    }
}