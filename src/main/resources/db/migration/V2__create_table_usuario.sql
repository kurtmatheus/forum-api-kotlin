create table usuario(
    id int not null auto_increment,
    nome varchar(50),
    email varchar(50),
    primary key (id)
);

insert into usuario(nome, email) values ('Dani Sampaio', 'dani@email.com');