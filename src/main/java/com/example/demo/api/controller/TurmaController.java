package com.example.demo.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Turma;
import com.example.demo.domain.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
	
	@Autowired
	private TurmaService turmaService;
	
	@GetMapping
	public List<Turma> listar(){
		return turmaService.buscarTurmas();
	}
	//busca turma
	@GetMapping("/{turmaId}")
	public ResponseEntity<Turma> buscar( @PathVariable Long turmaId) {
		if(!turmaService.existeTurmaPorId(turmaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Turma turmaRes = turmaService.buscarTurmaPorId(turmaId);
		
		return ResponseEntity.ok(turmaRes);
	}
	//adicionar turma
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Turma adicionar(@Valid @RequestBody Turma turma) {
		
		return turmaService.inserirTurma(turma);
		
	}
	//atualizar atravez do id
	@PutMapping("/{turmaId}")
	public ResponseEntity<Turma> atualizar(@Valid @PathVariable Long turmaId, @RequestBody Turma turma){
		if(!turmaService.existeTurmaPorId(turmaId)) {
			return ResponseEntity.notFound().build();
		}
		Turma TurmaRes=turmaService.atualizarTurma(turmaId, turma);
		
		return ResponseEntity.ok(TurmaRes);
	}
	//deletar professor
	@DeleteMapping("/{turmaId}")
	public ResponseEntity<Void> remover(@PathVariable Long turmaId){
		if(!turmaService.existeTurmaPorId(turmaId)) {
			return ResponseEntity.notFound().build();
		}
		
		turmaService.excluirTurma(turmaId);
		
		return ResponseEntity.noContent().build();
	}
	
}
