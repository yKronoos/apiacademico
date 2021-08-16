create table turma(
	id bigint auto_increment,
	turno varchar(60) not null,
	data_Inicio varchar(60) not null,
	data_Fim varchar(60),
	horario varchar(60),
	qtd_Vagas int not null,

	primary key(id)
);