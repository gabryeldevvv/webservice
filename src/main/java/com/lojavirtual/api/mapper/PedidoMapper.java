package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.model.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoRequestDTO dto) {
        return Pedido.builder()
                .valorTotal(dto.getValorTotal())
                .codigoRastreio(dto.getCodigoRastreio())
                .observacoes(dto.getObservacoes())
                .status("PENDENTE") // Default conforme entidade
                .build();
    }

    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .idPedido(pedido.getId_pedido())
                .clienteId(pedido.getCliente().getId())
                .clienteNome(pedido.getCliente().getNome())
                .enderecoEntrega(formatarEndereco(pedido.getEnderecoEntrega()))
                .dataPedido(pedido.getDataPedido())
                .status(pedido.getStatus())
                .valorTotal(pedido.getValorTotal())
                .codigoRastreio(pedido.getCodigoRastreio())
                .observacoes(pedido.getObservacoes())
                .itens(pedido.getItens().stream()
                        .map(this::toItemPedidoDTO)
                        .collect(Collectors.toList()))
                .pagamento(toPagamentoDTO(pedido.getPagamento()))
                .build();
    }

    private String formatarEndereco(Endereco endereco) {
        return endereco != null
                ? String.format("%s, %d - %s", endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro())
                : null;
    }

    private ItemPedidoResponseDTO toItemPedidoDTO(ItemPedido item) {
        return ItemPedidoResponseDTO.builder()
                .produtoId(item.getProduto().getId())
                .produtoNome(item.getProduto().getNome())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .build();
    }

    private PagamentoResponseDTO toPagamentoDTO(Pagamento pagamento) {
        return pagamento != null
                ? PagamentoResponseDTO.builder()
                .id(pagamento.getId())
                .tipo(pagamento.getTipo())
                .status(pagamento.getStatus())
                .build()
                : null;
    }
}