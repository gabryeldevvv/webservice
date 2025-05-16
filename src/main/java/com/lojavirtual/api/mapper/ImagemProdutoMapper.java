package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ImagemProdutoRequestDTO;
import com.lojavirtual.api.dto.ImagemProdutoResponseDTO;
import com.lojavirtual.api.model.ImagemProduto;
import org.springframework.stereotype.Component;

@Component
public class ImagemProdutoMapper {

    public ImagemProduto toEntity(ImagemProdutoRequestDTO dto) {
        return ImagemProduto.builder()
                .urlImagem(dto.getUrlImagem())
                .ordem(dto.getOrdem())
                .principal(dto.getPrincipal() != null ? dto.getPrincipal() : false)
                .build();
    }

    public ImagemProdutoResponseDTO toResponseDTO(ImagemProduto imagem) {
        return ImagemProdutoResponseDTO.builder()
                .id(imagem.getId())
                .produtoId(imagem.getProduto().getId())
                .produtoNome(imagem.getProduto().getNome())
                .urlImagem(imagem.getUrlImagem())
                .ordem(imagem.getOrdem())
                .principal(imagem.isPrincipal())
                .build();
    }

    public void updateEntityFromDTO(ImagemProdutoRequestDTO dto, ImagemProduto imagem) {
        if (dto.getUrlImagem() != null) imagem.setUrlImagem(dto.getUrlImagem());
        if (dto.getOrdem() != null) imagem.setOrdem(dto.getOrdem());
        if (dto.getPrincipal() != null) imagem.setPrincipal(dto.getPrincipal());
    }
}