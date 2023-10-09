create table odfs(

    id bigint not null auto_increment,

    odf varchar(20) not null,
    quantidade_pedido varchar(20) not null,
    quantidade_entrada varchar(20) not null,


    primary key(id)

);