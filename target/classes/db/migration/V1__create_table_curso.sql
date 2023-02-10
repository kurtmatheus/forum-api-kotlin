create table curso(
    id int not null auto_increment,
    nome varchar(50),
    categoria varchar(50),
    primary key (id)
);

insert into curso(nome, categoria) values ('Kotlin/Spring', 'Programacao');