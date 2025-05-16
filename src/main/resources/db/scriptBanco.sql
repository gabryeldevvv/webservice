-- Tabela de Cliente (atualizada)
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE,
    data_nascimento DATE,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de Endereço (nova)
CREATE TABLE endereco (
    id_endereco SERIAL PRIMARY KEY,
    id_cliente INTEGER REFERENCES cliente(id_cliente),
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(50) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado CHAR(2) NOT NULL,
    endereco_principal BOOLEAN DEFAULT FALSE
);

-- Tabela de Produto (atualizada)
CREATE TABLE produto (
    id_produto SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    preco_desconto DECIMAL(10,2),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    destaque BOOLEAN DEFAULT FALSE,
    SKU VARCHAR(50) UNIQUE NOT NULL
);

-- Tabela de Pedido (atualizada)
CREATE TABLE pedido (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente INTEGER REFERENCES cliente(id_cliente),
    id_endereco_entrega INTEGER REFERENCES endereco(id_endereco),
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDENTE',
    valor_total DECIMAL(10,2) NOT NULL,
    codigo_rastreio VARCHAR(50),
    observacoes TEXT
);

-- Tabela de Item_Pedido (atualizada)
CREATE TABLE item_pedido (
    id_item_pedido SERIAL PRIMARY KEY,
    id_pedido INTEGER REFERENCES pedido(id_pedido),
    id_produto INTEGER REFERENCES produto(id_produto),
    tamanho DECIMAL(4,1) NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    CHECK (quantidade > 0)
);

-- Tabela de Pagamento (atualizada)
CREATE TABLE pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    id_pedido INTEGER REFERENCES pedido(id_pedido),
    valor DECIMAL(10,2) NOT NULL,
    metodo_pagamento VARCHAR(50),
    status VARCHAR(50) DEFAULT 'PENDENTE',
    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    codigo_transacao VARCHAR(100)
);

-- Tabelas adicionais recomendadas (do modelo anterior)
CREATE TABLE marca (
    id_marca SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    descricao TEXT,
    website VARCHAR(100),
    ativa BOOLEAN DEFAULT TRUE
);

CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    descricao TEXT,
    ativa BOOLEAN DEFAULT TRUE,
    id_pai INTEGER REFERENCES categoria(id_categoria)
);

-- Adicionando relacionamentos faltantes
ALTER TABLE produto ADD COLUMN id_marca INTEGER REFERENCES marca(id_marca);
ALTER TABLE produto ADD COLUMN id_categoria INTEGER REFERENCES categoria(id_categoria);

-- Tabela de imagens do produto (nova)
CREATE TABLE imagem_produto (
    id_imagem SERIAL PRIMARY KEY,
    id_produto INTEGER REFERENCES produto(id_produto),
    url_imagem VARCHAR(255) NOT NULL,
    ordem INTEGER NOT NULL DEFAULT 1,
    principal BOOLEAN DEFAULT FALSE
);

-- Índices para melhorar performance
CREATE INDEX idx_produto_marca ON produto(id_marca);
CREATE INDEX idx_produto_categoria ON produto(id_categoria);
CREATE INDEX idx_pedido_cliente ON pedido(id_cliente);
CREATE INDEX idx_pedido_status ON pedido(status);
CREATE INDEX idx_item_pedido_produto ON item_pedido(id_produto);

-----------------------------------------------------------
-- NOVAS TABELAS (Abaixo são os acréscimos necessários) --
-----------------------------------------------------------

-- Tabela de Cor
CREATE TABLE cor (
    id_cor SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL,
    codigo_hex VARCHAR(7)
);

-- Tabela de Produto Variação
CREATE TABLE produto_variacao (
    id_variacao SERIAL PRIMARY KEY,
    id_produto INTEGER REFERENCES produto(id_produto),
    id_cor INTEGER REFERENCES cor(id_cor),
    nome_variacao VARCHAR(100) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de Tamanho
CREATE TABLE tamanho (
    id_tamanho SERIAL PRIMARY KEY,
    etiqueta VARCHAR(10) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    UNIQUE (etiqueta, tipo)
);

-- Tabela de Estoque atualizada para considerar variação e tamanho
CREATE TABLE estoque (
    id_estoque SERIAL PRIMARY KEY,
    id_variacao INTEGER REFERENCES produto_variacao(id_variacao),
    id_tamanho INTEGER REFERENCES tamanho(id_tamanho),
    quantidade INTEGER NOT NULL DEFAULT 0,
    CHECK (quantidade >= 0),
    UNIQUE (id_variacao, id_tamanho)
);

-- Tabela de Imagens por Variação
CREATE TABLE imagem_variacao (
    id_imagem SERIAL PRIMARY KEY,
    id_variacao INTEGER REFERENCES produto_variacao(id_variacao),
    url_imagem VARCHAR(255) NOT NULL,
    ordem INTEGER NOT NULL DEFAULT 1,
    principal BOOLEAN DEFAULT FALSE
);
