create table aluno(
	cpf bigint not null,
	nome varchar(60) not null,
	email varchar(60) not null,
	telefone varchar(60) not null,
	turma_id bigint not null,

	primary key(cpf)
	
);
alter table aluno add constraint fk_aluno_turma
foreign key (turma_id) references turma (id);