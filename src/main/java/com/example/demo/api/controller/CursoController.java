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

import com.example.demo.api.model.CursoInputModel;
import com.example.demo.api.model.CursoModel;
import com.example.demo.domain.model.Curso;
import com.example.demo.domain.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CursoModel> listar(){
		return toColletionModel(cursoService.buscarCursos());
	}
	//busca curso
	@GetMapping("/{cursoId}")
	public ResponseEntity<CursoModel> buscar( @PathVariable Long cursoId) {
		if(!cursoService.existeCursoPorId(cursoId)) {
			return ResponseEntity.notFound().build();
		}
		
		Curso cursoRes = cursoService.buscarCursoPorId(cursoId);
		
		CursoModel cursoModel = toModel(cursoRes);
		
		return ResponseEntity.ok(cursoModel);
	}
	//adicionar curso
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CursoModel adicionar(@Valid @RequestBody CursoInputModel cursoInput) {
		
		Curso curso=toEntity(cursoInput);
		
		Curso cursoRes = cursoService.inserirCurso(curso);
		
		CursoModel cursoModel = toModel(cursoRes);
		
		return cursoModel;
		
	}
	//atualizar atravez do id
	@PutMapping("/{cursoId}")
	public ResponseEntity<CursoModel> atualizar(@Valid @PathVariable Long cursoId, @RequestBody CursoInputModel cursoInput){
		
		Curso curso = toEntity(cursoInput);
		
		if(!cursoService.existeCursoPorId(cursoId)) {
			return ResponseEntity.notFound().build();
		}
		
		Curso c = cursoService.buscarCursoPorId(cursoId);
		curso.setStatus(c.getStatus());
		
		
		Curso cursoRes=cursoService.atualizarCurso(cursoId, curso);
		
		CursoModel cursoModel = toModel(cursoRes); 
		
		return ResponseEntity.ok(cursoModel);
	}
	//deletar curso
	@DeleteMapping("/{cursoId}")
	public ResponseEntity<Void> remover(@PathVariable Long cursoId){
		if(!cursoService.existeCursoPorId(cursoId)) {
			return ResponseEntity.notFound().build();
		}
		
		cursoService.excluirCurso(cursoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{cursoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long cursoId) {
		cursoService.finalizar(cursoId);
	}
	
	//Models
	private CursoModel toModel(Curso curso) {
		return modelMapper.map(curso, CursoModel.class);
	}
	private List<CursoModel> toColletionModel(List<Curso> cursos){
		return cursos.stream().map(curso -> toModel(curso)).collect(Collectors.toList());
	}
	//transforma o de representação para o de dominio
	private Curso toEntity(CursoInputModel cursoInputModel) {
		return modelMapper.map(cursoInputModel, Curso.class);
	}
}
