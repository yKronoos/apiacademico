package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	List<Professor> findByNomeContaining(String nome);
	List<Professor> findByNome(String nome);
	Professor findByEmail(String email);
	
}
