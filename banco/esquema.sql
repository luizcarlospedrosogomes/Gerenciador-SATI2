create table usuario(
	id serial,
	nome varchar(10),
	senha varchar(10)
);
alter table usuario
add constraint usuario_pkey primary key(id);

create table tipo_evento(
	id serial,
	descricao varchar(40)
);
alter table tipo_evento
add constraint tipo_evento_pkey primary key(id);

create table evento(
	id serial,
	nome varchar(50) not null,
	data_ini varchar(10) not null,
	data_fim varchar(10) not null,
	tipo_evento_id integer
);
alter table evento
add constraint evento_pkey primary key(id);
alter table evento
add constraint evento_tipo_evento_fk foreign key (tipo_evento_id) 
references tipo_evento(id);
alter table evento add column usuario_id integer
alter table evento
add constraint usuario_evento_id_fkey foreign key (usuario_id)
references usuario(id)
insert into tipo_evento(descricao) values('Palestra')
select id, descricao from tipo_evento
select * from usuario