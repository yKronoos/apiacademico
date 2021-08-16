package com.example.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Curso;
import com.example.demo.domain.model.Professor;
import com.example.demo.domain.model.StatusCurso;
import com.example.demo.domain.repository.CursoRepository;
import com.example.demo.domain.repository.ProfessorRepository;

@Service
public class CursoServiceImpl implements CursoService{
	
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private ProfessorRepository profRepository;
	
	@Override
	public Curso inserirCurso(Curso curso) {
		Curso cursoExistente=cursoRepository.findByNome(curso.getNome());
		
		if(cursoExistente!=null && !cursoExistente.equals(curso)) {
			throw new NegocioException("Já existe um curso cadastrado com esse nome");
		}
		//erifica se mandou o prof
		if(curso.getProf().getId() == null) {
			throw new NegocioException("Professor não informado");
		}
		
		//ver se há um professor
		Professor prof = profRepository.findById(curso.getProf().getId())
						.orElseThrow(() -> new NegocioException("Professor não encontrado"));
				
		
		curso.setProf(prof);
		curso.setStatus(StatusCurso.ABERTA);
		
		return cursoRepository.save(curso);
	}

	@Override
	public Curso atualizarCurso(Long cursoId, Curso curso) {
		if(!cursoRepository.existsById(cursoId)) {
			throw new NegocioException("Curso Inexistente");
		}
		//verifica se mandou o prof
		if(curso.getProf() == null) {
			throw new NegocioException("Professor não informado");
		}
		//ver se há um professor
		Professor prof = profRepository.findById(curso.getProf().getId())
				.orElseThrow(() -> new NegocioException("Professor não encontrado"));
		
		curso.setId(cursoId);
		curso.setProf(prof);
		curso=cursoRepository.save(curso);
		
		return curso;
	}

	@Override
	public Curso excluirCurso(Long cursoId) {
		var curso = buscarCursoPorId(cursoId);
		
		if(!cursoRepository.existsById(cursoId)) {
			return null;
		}
		
		cursoRepository.deleteById(cursoId);
		
		return curso;
	}

	@Override
	public List<Curso> buscarCursos() {
		return cursoRepository.findAll();
	}

	@Override
	public Curso buscarCursoPorId(Long cursoId) {
		Optional<Curso> curso= cursoRepository.findById(cursoId);
		
		if(curso.isPresent()) {
			return curso.get();
		}
		
		return null;
	}

	@Override
	public Boolean existeCursoPorId(Long cursoId) {
		if(!cursoRepository.existsById(cursoId)) {
			return false;
		}
		return true;
	}

	@Override
	public void finalizar(Long cursoId) {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new NegocioException("Curso não Encontrado"));
		
		curso.finalizar();
		
		cursoRepository.save(curso);
	}



}
