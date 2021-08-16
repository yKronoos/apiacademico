package com.example.demo.api.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AlunoInputModel {
	@NotNull
	private Long cpf;
	@NotBlank	
	private String nome;
	@NotBlank
	@Email
	private String email;
	@NotBlank	
	private String telefone;
	@Valid
	@NotNull
	private TurmaIdInput turma;
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
	public TurmaIdInput getTurma() {
		return turma;
	}
	public void setTurma(TurmaIdInput turma) {
		this.turma = turma;
	}
	
	
}
