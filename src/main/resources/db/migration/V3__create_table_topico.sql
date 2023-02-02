create table topico(
    id int not null auto_increment,
    titulo varchar(50) not null,
    mensagem varchar(300) not null,
    data_criacao datetime not null,
    status varchar(20) not null,
    curso_id int not null,
    autor_id int not null,
    primary key (id),
    foreign key(curso_id) references curso(id),
    foreign key(autor_id) references usuario(id)
);
