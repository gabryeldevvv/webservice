package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.ClienteRequestDTO;
import com.lojavirtual.api.dto.ClienteResponseDTO;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.ClienteMapper;
import com.lojavirtual.api.model.Cliente;
import com.lojavirtual.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final PasswordEncoder passwordEncoder;

    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        // Validações de unicidade
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new EmailJaCadastradoException(dto.getEmail());
        }
        if (clienteRepository.existsByCpf(dto.getCpf().replaceAll("[^0-9]", ""))) {
            throw new CpfJaCadastradoException(dto.getCpf());
        }

        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setSenhaHash(passwordEncoder.encode(dto.getSenha())); // Hash da senha

        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(salvo);
    }

    public List<ClienteResponseDTO> listarClientes(String busca) {
        return (busca != null && !busca.isBlank())
                ? clienteRepository.findByNomeContainingIgnoreCase(busca)
                .stream()
                .map(clienteMapper::toResponseDTO)
                .toList()
                : clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponseDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponseDTO)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));

        // Valida email único (se alterado)
        if (dto.getEmail() != null && !dto.getEmail().equals(cliente.getEmail())) {
            if (clienteRepository.existsByEmail(dto.getEmail())) {
                throw new EmailJaCadastradoException(dto.getEmail());
            }
            cliente.setEmail(dto.getEmail());
        }

        clienteMapper.updateEntityFromDTO(dto, cliente);
        return clienteMapper.toResponseDTO(clienteRepository.save(cliente));
    }

    public void desativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}