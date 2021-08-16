package com.example.demo.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.NegocioException;
import com.example.demo.domain.model.Professor;
import com.example.demo.domain.repository.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService{

	@Autowired
	private ProfessorRepository profRepository;
	
	
	@Override
	public Professor inserirProfessor(Professor prof) {
		Professor profExistente=profRepository.findByEmail(prof.getEmail());
		
		if(profExistente!=null && !profExistente.equals(prof)) {
			throw new NegocioException("Já existe um professor cadastrado com esse email");
		}
		
		
		return profRepository.save(prof);
	}

	@Override
	public Professor atualizarProfessor(Long profId, Professor prof) {
		if(!profRepository.existsById(profId)) {
			return null;
		}
		prof.setId(profId);
		prof = profRepository.save(prof);
		return prof;
	}

	@Override
	public Professor excluirProfessor(Long profId) {
		var prof = buscarProfessorPorId(profId);
		
		if(!profRepository.existsById(profId)) {
			return null;
		}
		
		profRepository.deleteById(profId);
		
		return prof;
	}

	@Override
	public List<Professor> buscarProfessores() {
		return profRepository.findAll();
	}

	@Override
	public Professor buscarProfessorPorId(Long profId) {
		Optional<Professor> prof = profRepository.findById(profId);
		
		if(prof.isPresent()) {
			return prof.get();
		}
		
		return null;
	}

	@Override
	public Professor buscarProfessorPorEmail(String email) {
		Professor prof = profRepository.findByEmail(email);
		
		if(prof!=null) {
			return prof;
		}
		
		return null;
	}

	@Override
	public Boolean existeProfessorPorId(Long profId) {
		if(!profRepository.existsById(profId)) {
			return false;
		}
		
		return true;
	}

}
