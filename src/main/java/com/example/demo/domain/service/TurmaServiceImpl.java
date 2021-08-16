package com.example.demo.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Turma;
import com.example.demo.domain.repository.TurmaRepository;

@Service
public class TurmaServiceImpl implements TurmaService{

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Override
	public Turma inserirTurma(Turma turma) {
		turma.setDataInicio(OffsetDateTime.now());
				
		return turmaRepository.save(turma);
	}

	@Override
	public Turma atualizarTurma(Long turmaId, Turma turma) {
		
		Turma t=buscarTurmaPorId(turmaId);
		
		if(!turmaRepository.existsById(turmaId)) {
			return null;
		}
		turma.setId(turmaId);
		turma.setDataInicio(t.getDataInicio());
		turma = turmaRepository.save(turma);
		return turma;
	}

	@Override
	public Turma excluirTurma(Long turmaId) {
		var turma = buscarTurmaPorId(turmaId);
		
		if(!turmaRepository.existsById(turmaId)) {
			return null;
		}
		
		turmaRepository.deleteById(turmaId);
		
		return turma;
	}

	@Override
	public List<Turma> buscarTurmas() {
		return turmaRepository.findAll();
	}

	@Override
	public Turma buscarTurmaPorId(Long turmaId) {
		Optional<Turma> prof = turmaRepository.findById(turmaId);
		
		if(prof.isPresent()) {
			return prof.get();
		}
		
		return null;
	}

	@Override
	public Boolean existeTurmaPorId(Long turmaId) {
		if(!turmaRepository.existsById(turmaId)) {
			return false;
		}
		
		return true;
	}

}
