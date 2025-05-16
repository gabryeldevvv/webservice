package com.lojavirtual.api.mapper;

import com.lojavirtual.api.dto.PagamentoRequestDTO;
import com.lojavirtual.api.dto.PagamentoResponseDTO;
import com.lojavirtual.api.model.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoRequestDTO dto) {
        return Pagamento.builder()
                .valor(dto.getValor())
                .metodoPagamento(dto.getMetodoPagamento())
                .status(dto.getStatus())
                .codigoTransacao(dto.getCodigoTransacao())
                .build();
    }

    public PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        return PagamentoResponseDTO.builder()
                .idPagamento(pagamento.getId_pagamento())
                .pedidoId(pagamento.getPedido().getId_pedido())
                .valor(pagamento.getValor())
                .metodoPagamento(pagamento.getMetodoPagamento())
                .status(pagamento.getStatus())
                .dataPagamento(pagamento.getDataPagamento())
                .codigoTransacao(pagamento.getCodigoTransacao())
                .build();
    }

    public void updateEntityFromDTO(PagamentoRequestDTO dto, Pagamento pagamento) {
        if (dto.getValor() != null) pagamento.setValor(dto.getValor());
        if (dto.getMetodoPagamento() != null) pagamento.setMetodoPagamento(dto.getMetodoPagamento());
        if (dto.getStatus() != null) pagamento.setStatus(dto.getStatus());
        if (dto.getCodigoTransacao() != null) pagamento.setCodigoTransacao(dto.getCodigoTransacao());
    }
}