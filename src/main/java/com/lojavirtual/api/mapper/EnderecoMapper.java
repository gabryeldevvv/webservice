package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.EnderecoRequestDTO;
import com.lojavirtual.api.dto.EnderecoResponseDTO;
import com.lojavirtual.api.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequestDTO dto) {
        return Endereco.builder()
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .enderecoPrincipal(dto.getEnderecoPrincipal() != null ? dto.getEnderecoPrincipal() : false)
                .build();
    }

    public EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        return EnderecoResponseDTO.builder()
                .id(endereco.getId())
                .clienteId(endereco.getCliente().getId())
                .clienteNome(endereco.getCliente().getNome())
                .cep(endereco.getCep())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .enderecoPrincipal(endereco.isEnderecoPrincipal())
                .build();
    }

    public void updateEntityFromDTO(EnderecoRequestDTO dto, Endereco endereco) {
        if (dto.getCep() != null) endereco.setCep(dto.getCep());
        if (dto.getLogradouro() != null) endereco.setLogradouro(dto.getLogradouro());
        if (dto.getNumero() != null) endereco.setNumero(dto.getNumero());
        if (dto.getComplemento() != null) endereco.setComplemento(dto.getComplemento());
        if (dto.getBairro() != null) endereco.setBairro(dto.getBairro());
        if (dto.getCidade() != null) endereco.setCidade(dto.getCidade());
        if (dto.getEstado() != null) endereco.setEstado(dto.getEstado());
        if (dto.getEnderecoPrincipal() != null) endereco.setEnderecoPrincipal(dto.getEnderecoPrincipal());
    }
}