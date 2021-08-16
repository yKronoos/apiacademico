package com.example.demo.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nome);
	
}
