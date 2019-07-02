create database ecommerce;

use ecommerce;

create table dados_pessoais(
id integer auto_increment not null,
nome varchar(50) not null,
sobrenome varchar (100) not null,
cpf varchar(11) not null,
telefone varchar(11) not null,
email varchar(100) not null,
primary key (id)
);

create table endereco(
id integer auto_increment not null,
logradouro varchar (255) not null,
numero integer not null,
cep varchar(8) not null,
bairro varchar(255) not null,
cidade varchar(255) not null,
estado varchar(50) not null,
complemento varchar(255),
referencia text,
primary key (id)
);

create table produto (
id integer auto_increment not null,
nome varchar(255) not null,
descricao text not null,
preco decimal(7,2) not null,
primary key (id)
);

create table pedido(
id integer auto_increment not null,
preco_total decimal(7,2) not null,
frete decimal (7,2) not null,
numero_pedido integer not null,
id_produto integer not null,
primary key (id)
);
alter table pedido add constraint fk_produto_pedido foreign key (id_produto) references produto(id);

create table cliente(
id integer auto_increment not null,
id_dados_pessoais integer not null,
id_endereco integer not null,
id_pedido integer not null,
primary key (id)
);
alter table cliente add  constraint fk_dados_pessoais_cliente foreign key(id_dados_pessoais) references dados_pessoais(id);
alter table cliente add  constraint fk_endereco_cliente foreign key(id_endereco) references endereco(id);
alter table cliente add  constraint fk_pedidos_cliente foreign key(id_pedido) references pedido(id);

create table usuario(
id integer auto_increment not null,
login varchar(30) not null,
password varchar (30) not null,
id_cliente integer not null,
primary key (id)
);
alter table usuario add constraint fk_cliente_usuario  foreign key (id_cliente) references cliente(id);

drop database ecommerce;