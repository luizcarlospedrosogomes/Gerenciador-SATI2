/*
create table evento(id serial,
		    nome varchar(60),
		    data varchar(14),
		    horaini varchar(5),
		    horafim varchar(5),
		    tipo integer
);
alter table evento
add constraint evento_pkey primary key (id);

ALTER TABLE evento
ADD CONSTRAINT evento_tipo_fk FOREIGN KEY (tipo)
REFERENCES tipo_evento(id);
*/
--insert into tipo_evento (descricao) values('outros');
--, 'minicursos', 'mesa redonda', 'oficina', 'outros'
select * from tipo_evento 