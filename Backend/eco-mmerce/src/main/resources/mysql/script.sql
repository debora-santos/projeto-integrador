CREATE DATABASE ecommerce;

USE ecommerce;

CREATE TABLE dados_pessoais (
    id INTEGER AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE endereco (
    id INTEGER AUTO_INCREMENT NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    cep VARCHAR(8) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(255),
    referencia TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE produto (
    id INTEGER AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    preco DECIMAL(7 , 2 ) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE pedido (
    id INTEGER AUTO_INCREMENT NOT NULL,
    preco_total DECIMAL(7 , 2 ) NOT NULL,
    frete DECIMAL(7 , 2 ) NOT NULL,
    numero_pedido INTEGER NOT NULL,
    id_produto INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE pedido ADD CONSTRAINT fk_produto_pedido FOREIGN KEY (id_produto) REFERENCES produto(id);

CREATE TABLE cliente (
    id INTEGER AUTO_INCREMENT NOT NULL,
    id_dados_pessoais INTEGER NOT NULL,
    id_endereco INTEGER NOT NULL,
    id_pedido INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE cliente ADD CONSTRAINT fk_dados_pessoais_cliente FOREIGN KEY(id_dados_pessoais) REFERENCES dados_pessoais(id);

ALTER TABLE cliente ADD CONSTRAINT fk_endereco_cliente FOREIGN KEY(id_endereco) REFERENCES endereco(id);

ALTER TABLE cliente ADD CONSTRAINT fk_pedidos_cliente FOREIGN KEY(id_pedido) REFERENCES pedido(id);

CREATE TABLE usuario (
    id INTEGER AUTO_INCREMENT NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    id_cliente INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE usuario ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY(id_cliente) REFERENCES cliente(id);