package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.PagamentoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoMapper pagamentoMapper;

    public PagamentoResponseDTO criarPagamento(PagamentoRequestDTO dto) {
        // Valida código de transação único
        if (dto.getCodigoTransacao() != null &&
                pagamentoRepository.existsByCodigoTransacao(dto.getCodigoTransacao())) {
            throw new CodigoTransacaoExistenteException(dto.getCodigoTransacao());
        }

        Pagamento pagamento = pagamentoMapper.toEntity(dto);

        // Resolve relacionamento com Pedido
        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new PedidoNaoEncontradoException(dto.getPedidoId()));
        pagamento.setPedido(pedido);

        Pagamento salvo = pagamentoRepository.save(pagamento);
        return pagamentoMapper.toResponseDTO(salvo);
    }

    public PagamentoResponseDTO atualizarStatus(Long id, String novoStatus) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(id));

        pagamento.setStatus(novoStatus);
        Pagamento atualizado = pagamentoRepository.save(pagamento);

        // Atualiza status do pedido se necessário
        if ("CONCLUIDO".equals(novoStatus)) {
            atualizado.getPedido().setStatus("PAGO");
            pedidoRepository.save(atualizado.getPedido());
        }

        return pagamentoMapper.toResponseDTO(atualizado);
    }

    public List<PagamentoResponseDTO> listarTodos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamentoMapper::toResponseDTO)
                .toList();
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .map(pagamentoMapper::toResponseDTO)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(id));
    }

    public List<PagamentoResponseDTO> listarPorStatus(String status) {
        return pagamentoRepository.findByStatus(status)
                .stream()
                .map(pagamentoMapper::toResponseDTO)
                .toList();
    }
}