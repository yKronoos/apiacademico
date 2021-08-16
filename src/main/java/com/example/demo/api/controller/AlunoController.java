package com.example.demo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.example.demo.api.model.AlunoInputModel;
import com.example.demo.api.model.AlunoModel;
import com.example.demo.domain.model.Aluno;
import com.example.demo.domain.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	/*test*/
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<AlunoModel> listar(){
		return toColletionModel(alunoService.buscarAlunos());
	}
	//busca curso
	@GetMapping("/{alunoCpf}")
	public ResponseEntity<AlunoModel> buscar( @PathVariable Long alunoCpf) {
		if(!alunoService.existeAlunoPorCpf(alunoCpf)) {
			return ResponseEntity.notFound().build();
		}
		
		Aluno alunoRes = alunoService.buscarAlunoPorCpf(alunoCpf);
		
		AlunoModel alunoModel=toModel(alunoRes);
		
		return ResponseEntity.ok(alunoModel);
	}
	//adicionar curso
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoModel adicionar(@Valid @RequestBody AlunoInputModel alunoInput) {
		
		Aluno aluno=toEntity(alunoInput);
		
		Aluno alunoRes = alunoService.inserirAluno(aluno);
		
		AlunoModel alunoModel = toModel(alunoRes);
		
		return alunoModel;
		
	}
	//atualizar atravez do id
	@PutMapping("/{alunoCpf}")
	public ResponseEntity<AlunoModel> atualizar(@Valid @PathVariable Long alunoCpf, @RequestBody AlunoInputModel alunoInput){
		Aluno aluno = toEntity(alunoInput);
		
		if(!alunoService.existeAlunoPorCpf(alunoCpf)) {
			return ResponseEntity.notFound().build();
		}
		
		//Aluno a = alunoService.buscarAlunoPorCpf(alunoCpf);	
		
		Aluno alunoRes=alunoService.atualizarAluno(alunoCpf, aluno);
		
		AlunoModel alunoModel = toModel(alunoRes); 
		
		return ResponseEntity.ok(alunoModel);
	}
	//deletar curso
	@DeleteMapping("/{alunoCpf}")
	public ResponseEntity<Void> remover(@PathVariable Long alunoCpf){
		if(!alunoService.existeAlunoPorCpf(alunoCpf)) {
			return ResponseEntity.notFound().build();
		}
		
		alunoService.excluirAluno(alunoCpf);
		
		return ResponseEntity.noContent().build();
	}
	//Models
	private AlunoModel toModel(Aluno aluno) {
		return modelMapper.map(aluno, AlunoModel.class);
	}
	private List<AlunoModel> toColletionModel(List<Aluno> alunos){
		return alunos.stream().map(aluno -> toModel(aluno)).collect(Collectors.toList());
	}
	//transforma o de representação para o de dominio
	private Aluno toEntity(AlunoInputModel alunoInputModel) {
		return modelMapper.map(alunoInputModel, Aluno.class);
	}
}
