package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.PedidoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido = pedidoMapper.toEntity(dto);

        // Resolver relacionamentos
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException(dto.getClienteId()));
        pedido.setCliente(cliente);

        if (dto.getEnderecoEntregaId() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getEnderecoEntregaId())
                    .orElseThrow(() -> new EnderecoNaoEncontradoException(dto.getEnderecoEntregaId()));
            pedido.setEnderecoEntrega(endereco);
        }

        // Lógica para calcular valor total (se necessário)
        // pedido.setValorTotal(calcularTotal(pedido.getItens()));

        Pedido salvo = pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(salvo);
    }

    public List<PedidoResponseDTO> listarPedidos(String status, Long clienteId) {
        if (clienteId != null) {
            return pedidoRepository.findByClienteId(clienteId)
                    .stream()
                    .map(pedidoMapper::toResponseDTO)
                    .toList();
        }
        if (status != null) {
            return pedidoRepository.findByStatus(status)
                    .stream()
                    .map(pedidoMapper::toResponseDTO)
                    .toList();
        }
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toResponseDTO)
                .toList();
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toResponseDTO)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }
}