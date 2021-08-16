package com.example.demo.api.model;

public class AlunoModel {
	private Long cpf;
	private String nome;
	private String email;
	private String telefone;
	private TurmaResumoModel turma;
	
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public TurmaResumoModel getTurma() {
		return turma;
	}
	public void setTurma(TurmaResumoModel turma) {
		this.turma = turma;
	}
	
	
}
