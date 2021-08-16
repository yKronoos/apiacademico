package com.example.demo.domain.service;

import java.util.List;

import com.example.demo.domain.model.Aluno;

public interface AlunoService {
	public abstract Aluno inserirAluno(Aluno aluno);
	public abstract Aluno atualizarAluno(Long alunoCpf, Aluno aluno);
	public abstract Aluno excluirAluno(Long alunoCpf);
	public abstract List<Aluno> buscarAlunos();
	public abstract Aluno buscarAlunoPorCpf(Long alunoCpf);
	public abstract Boolean existeAlunoPorCpf(Long alunoCpf);
}
