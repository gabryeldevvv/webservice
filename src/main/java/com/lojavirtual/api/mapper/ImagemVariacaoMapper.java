package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ImagemVariacaoRequestDTO;
import com.lojavirtual.api.dto.ImagemVariacaoResponseDTO;
import com.lojavirtual.api.model.ImagemVariacao;
import org.springframework.stereotype.Component;

@Component
public class ImagemVariacaoMapper {

    public ImagemVariacao toEntity(ImagemVariacaoRequestDTO dto) {
        return ImagemVariacao.builder()
                .urlImagem(dto.getUrlImagem())
                .ordem(dto.getOrdem())
                .principal(dto.getPrincipal() != null ? dto.getPrincipal() : false)
                .build();
    }

    public ImagemVariacaoResponseDTO toResponseDTO(ImagemVariacao imagem) {
        return ImagemVariacaoResponseDTO.builder()
                .id(imagem.getId())
                .idVariacao(imagem.getVariacao().getId())
                .urlImagem(imagem.getUrlImagem())
                .ordem(imagem.getOrdem())
                .principal(imagem.isPrincipal())
                .build();
    }

    public void updateEntityFromDTO(ImagemVariacaoRequestDTO dto, ImagemVariacao imagem) {
        if (dto.getUrlImagem() != null) imagem.setUrlImagem(dto.getUrlImagem());
        if (dto.getOrdem() != null) imagem.setOrdem(dto.getOrdem());
        if (dto.getPrincipal() != null) imagem.setPrincipal(dto.getPrincipal());
    }
}