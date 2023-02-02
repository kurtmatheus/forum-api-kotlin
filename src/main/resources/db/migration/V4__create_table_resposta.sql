create table resposta(
    id int not null auto_increment,
    mensagem varchar(300) not null,
    data_criacao datetime not null,
    topico_id int not null,
    autor_id int not null,
    solucao bit not null,
    primary key (id),
    foreign key(topico_id) references topico(id),
    foreign key(autor_id) references usuario(id)
);
