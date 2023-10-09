create table produtos(

    id bigint not null auto_increment,
    status_produto varchar(10),
    categoria varchar(10),
    familia varchar(10),
    empresa varchar(10) not null,
    codigo varchar(10) not null,
    descricao varchar(100) not null,
    local_estoque varchar(10) not null,
    quantidade varchar(20) not null,
    estoque_minimo varchar(20) not null,
    estoque_maximo varchar(20) not null,

    primary key(id)
);