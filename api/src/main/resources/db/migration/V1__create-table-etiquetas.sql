create table etiquetas(

    id bigint not null auto_increment,

    serie varchar(30),
    odf varchar(30) not null,
    data_apontamento datetime null,
    bip varchar(3) not null,
    quantidade_odf varchar(10) not null,
    quanitdade_utilizada varchar(10) not null,


    primary key(id)

);