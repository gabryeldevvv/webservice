package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.EnderecoRequestDTO;
import com.lojavirtual.api.dto.EnderecoResponseDTO;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.EnderecoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoResponseDTO criarEndereco(EnderecoRequestDTO dto) {
        Endereco endereco = enderecoMapper.toEntity(dto);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException(dto.getClienteId()));
        endereco.setCliente(cliente);

        // Lógica para atualizar endereço principal
        if (Boolean.TRUE.equals(dto.getEnderecoPrincipal())) {
            enderecoRepository.desmarcarEnderecosPrincipais(cliente.getId());
        }

        Endereco salvo = enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(salvo);
    }

    public List<EnderecoResponseDTO> listarEnderecos(Long clienteId, String busca) {
        if (clienteId != null) {
            return enderecoRepository.findByClienteId(clienteId)
                    .stream()
                    .map(enderecoMapper::toResponseDTO)
                    .toList();
        }
        if (busca != null && !busca.isBlank()) {
            return enderecoRepository.findByCepContainingOrLogradouroContainingAllIgnoreCase(busca, busca)
                    .stream()
                    .map(enderecoMapper::toResponseDTO)
                    .toList();
        }
        return enderecoRepository.findAll()
                .stream()
                .map(enderecoMapper::toResponseDTO)
                .toList();
    }

    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoRequestDTO dto) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException(id));

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new ClienteNaoEncontradoException(dto.getClienteId()));
            endereco.setCliente(cliente);
        }

        enderecoMapper.updateEntityFromDTO(dto, endereco);

        // Atualização de endereço principal
        if (Boolean.TRUE.equals(dto.getEnderecoPrincipal())) {
            enderecoRepository.desmarcarEnderecosPrincipais(endereco.getCliente().getId());
        }

        return enderecoMapper.toResponseDTO(enderecoRepository.save(endereco));
    }

    public void excluirEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoNaoEncontradoException(id);
        }
        enderecoRepository.deleteById(id);
    }
}