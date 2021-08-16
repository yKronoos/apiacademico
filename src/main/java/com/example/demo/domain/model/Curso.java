package com.example.demo.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;

import com.example.demo.domain.ValidationGroup;
import com.example.demo.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@Entity
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=60)
	private String nome;
	
	private String descricao;
	
	private BigDecimal preco;
	
	@Size(max=20)
	private String nivel;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusCurso status;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroup.ProfessorId.class)
	@NotNull
	@ManyToOne	
	private Professor prof;
	
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
	public Professor getProf() {
		return prof;
	}
	public void setProf(Professor professor) {
		this.prof = professor;
	}
	
	//methods
	public boolean podeSerFinalizada() {
		return StatusCurso.ABERTA.equals(getStatus());
	}
	public boolean naoPodeSerFinalizada() {
		return !podeSerFinalizada();
	}
	public void finalizar() {
		if(naoPodeSerFinalizada()) {
			throw new NegocioException("Curso não pode ser finalizado");
		}
		
		setStatus(StatusCurso.FINALIZADA);
	}
	
}
