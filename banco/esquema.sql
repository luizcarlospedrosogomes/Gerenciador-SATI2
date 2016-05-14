create table usuario(
	id serial,
	nome varchar(10),
	senha varchar(10)
);
alter table usuario
add constraint usuario_pkey primary key(id);