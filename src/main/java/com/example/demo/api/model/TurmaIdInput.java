package com.example.demo.api.model;

import javax.validation.constraints.NotNull;

import com.example.demo.domain.ValidationGroup;

public class TurmaIdInput {
	@NotNull(groups = ValidationGroup.ProfessorId.class)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
