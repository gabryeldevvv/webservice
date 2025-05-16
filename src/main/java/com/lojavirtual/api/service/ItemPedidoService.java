package com.lojavirtual.api.service;

import com.lojavirtual.api.dto.*;
import com.lojavirtual.api.exception.*;
import com.lojavirtual.api.mapper.ItemPedidoMapper;
import com.lojavirtual.api.model.*;
import com.lojavirtual.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemPedidoService {

    private final ItemPedidoRepository itemRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemMapper;

    public ItemPedidoResponseDTO criarItem(ItemPedidoRequestDTO dto) {
        ItemPedido item = itemMapper.toEntity(dto);

        Pedido pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new PedidoNaoEncontradoException(dto.getPedidoId()));
        item.setPedido(pedido);

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(dto.getProdutoId()));
        item.setProduto(produto);

        ItemPedido salvo = itemRepository.save(item);
        return itemMapper.toResponseDTO(salvo);
    }

    public List<ItemPedidoResponseDTO> listarItens(Long pedidoId, Long produtoId) {
        if (pedidoId != null) {
            return itemRepository.findByPedidoId(pedidoId)
                    .stream()
                    .map(itemMapper::toResponseDTO)
                    .toList();
        }
        if (produtoId != null) {
            return itemRepository.findByProdutoId(produtoId)
                    .stream()
                    .map(itemMapper::toResponseDTO)
                    .toList();
        }
        return itemRepository.findAll()
                .stream()
                .map(itemMapper::toResponseDTO)
                .toList();
    }

    public ItemPedidoResponseDTO atualizarItem(Long id, ItemPedidoRequestDTO dto) {
        ItemPedido item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemPedidoNaoEncontradoException(id));

        itemMapper.updateEntityFromDTO(dto, item);
        return itemMapper.toResponseDTO(itemRepository.save(item));
    }

    public void excluirItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemPedidoNaoEncontradoException(id);
        }
        itemRepository.deleteById(id);
    }
}