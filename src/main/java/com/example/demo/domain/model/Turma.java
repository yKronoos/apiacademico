package com.example.demo.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.ValidationGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Turma {
	
	@NotNull(groups = ValidationGroup.TurmaId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String turno;
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataInicio;
	
	private OffsetDateTime dataFim;
	@NotNull
	private OffsetDateTime horario;
	@NotNull
	private int qtdVagas;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public OffsetDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(OffsetDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public OffsetDateTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(OffsetDateTime dataFim) {
		this.dataFim = dataFim;
	}
	public OffsetDateTime getHorario() {
		return horario;
	}
	public void setHorario(OffsetDateTime horario) {
		this.horario = horario;
	}
	public int getQtdVagas() {
		return qtdVagas;
	}
	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}
	
	
}
