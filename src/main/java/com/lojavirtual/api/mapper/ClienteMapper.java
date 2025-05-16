package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.ClienteRequestDTO;
import com.lojavirtual.api.dto.ClienteResponseDTO;
import com.lojavirtual.api.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto) {
        return Cliente.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senhaHash(dto.getSenha()) // Deve ser hasheada no service
                .telefone(dto.getTelefone())
                .cpf(dto.getCpf().replaceAll("[^0-9]", "")) // Remove formatação
                .dataNascimento(dto.getDataNascimento())
                .ativo(dto.getAtivo() != null ? dto.getAtivo() : true)
                .build();
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .cpf(formatarCpf(cliente.getCpf()))

                .build();
    }

    private String formatarCpf(String cpf) {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public void updateEntityFromDTO(ClienteRequestDTO dto, Cliente cliente) {
        if (dto.getNome() != null) cliente.setNome(dto.getNome());
        if (dto.getTelefone() != null) cliente.setTelefone(dto.getTelefone());
        if (dto.getDataNascimento() != null) cliente.setDataNascimento(dto.getDataNascimento());
        if (dto.getAtivo() != null) cliente.setAtivo(dto.getAtivo());
    }
}