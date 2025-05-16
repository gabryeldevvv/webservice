package com.lojavirtual.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class EnderecoResponseDTO {
    private Long id;
    private Long clienteId;
    private String clienteNome;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Boolean enderecoPrincipal;
}