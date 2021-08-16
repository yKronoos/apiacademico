package com.example.demo.api.model;

import java.math.BigDecimal;

import com.example.demo.domain.model.StatusCurso;

public class CursoModel {
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String nivel;
	private StatusCurso status;
	private ProfessorResumoModel prof;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public StatusCurso getStatus() {
		return status;
	}
	public void setStatus(StatusCurso status) {
		this.status = status;
	}
	public ProfessorResumoModel getProf() {
		return prof;
	}
	public void setProf(ProfessorResumoModel prof) {
		this.prof = prof;
	}
	
	
}
