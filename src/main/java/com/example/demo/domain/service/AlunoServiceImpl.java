package com.example.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.model.Turma;
import com.example.demo.domain.repository.AlunoRepository;
import com.example.demo.domain.repository.TurmaRepository;

@Service
public class AlunoServiceImpl implements AlunoService{
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	

	@Override
	public Aluno inserirAluno(Aluno aluno) {
		Aluno alunoExistente=alunoRepository.findByCpf(aluno.getCpf());
		
		if(alunoExistente!=null && !alunoExistente.equals(aluno)) {
			throw new NegocioException("Já existe um aluno cadastrado com esse cpf");
		}
		//erifica se mandou o prof
		if(aluno.getTurma().getId() == null) {
			throw new NegocioException("Turma não informada");
		}
		
		//ver se há um professor
		Turma turma = turmaRepository.findById(aluno.getTurma().getId())
						.orElseThrow(() -> new NegocioException("Turma não encontrada"));
				
		
		aluno.setTurma(turma);
		
		return alunoRepository.save(aluno);
	}

	@Override
	public Aluno atualizarAluno(Long alunoCpf, Aluno aluno) {
		if(!alunoRepository.existsById(alunoCpf)) {
			throw new NegocioException("Aluno Inexistente");
		}
		//verifica se mandou a turma
		if(aluno.getTurma().getId() == null) {
			throw new NegocioException("Turma não informada");
		}
		//ver se há um turma
		Turma turma = turmaRepository.findById(aluno.getTurma().getId())
						.orElseThrow(() -> new NegocioException("Turma não encontrada"));
		
		aluno.setCpf(alunoCpf);
		aluno.setTurma(turma);
		aluno=alunoRepository.save(aluno);
		
		return aluno;
	}

	@Override
	public Aluno excluirAluno(Long alunoCpf) {
		var aluno = buscarAlunoPorCpf(alunoCpf);
		
		if(!alunoRepository.existsById(alunoCpf)) {
			return null;
		}
		
		alunoRepository.deleteById(alunoCpf);
		
		return aluno;
	}

	@Override
	public List<Aluno> buscarAlunos() {
		return alunoRepository.findAll();
	}

	@Override
	public Aluno buscarAlunoPorCpf(Long alunoCpf) {
		Optional<Aluno> aluno= alunoRepository.findById(alunoCpf);
		
		if(aluno.isPresent()) {
			return aluno.get();
		}
		
		return null;
	}

	@Override
	public Boolean existeAlunoPorCpf(Long alunoCpf) {
		if(!alunoRepository.existsById(alunoCpf)) {
			return false;
		}
		return true;
	}
	
	
}
