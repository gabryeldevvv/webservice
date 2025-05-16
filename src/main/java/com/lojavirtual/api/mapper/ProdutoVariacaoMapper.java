package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ProdutoVariacaoRequestDTO;
import com.lojavirtual.api.dto.ProdutoVariacaoResponseDTO;
import com.lojavirtual.api.model.ProdutoVariacao;
import org.springframework.stereotype.Component;

@Component
public class ProdutoVariacaoMapper {

    public ProdutoVariacao toEntity(ProdutoVariacaoRequestDTO dto) {
        return ProdutoVariacao.builder()
                .nomeVariacao(dto.getNomeVariacao())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
    }

    public ProdutoVariacaoResponseDTO toResponseDTO(ProdutoVariacao variacao) {
        return ProdutoVariacaoResponseDTO.builder()
                .id(variacao.getId())
                .nomeVariacao(variacao.getNomeVariacao())
                .ativo(variacao.isAtivo())
                .idProduto(variacao.getProduto().getId())
                .nomeProduto(variacao.getProduto().getNome())
                .idCor(variacao.getCor() != null ? variacao.getCor().getId() : null)
                .nomeCor(variacao.getCor() != null ? variacao.getCor().getNome() : null)
                .codigoHexCor(variacao.getCor() != null ? variacao.getCor().getCodigoHex() : null)
                .build();
    }

    public void updateEntityFromDTO(ProdutoVariacaoRequestDTO dto, ProdutoVariacao variacao) {
        if (dto.getNomeVariacao() != null) {
            variacao.setNomeVariacao(dto.getNomeVariacao());
        }
        if (dto.getAtivo() != null) {
            variacao.setAtivo(dto.getAtivo());
        }
    }
}