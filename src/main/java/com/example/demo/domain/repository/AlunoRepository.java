package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	Aluno findByCpf(Long cpf);
	@Query(value="DELETE FROM aluno WHERE cpf = ?1", nativeQuery = true)
	Aluno deleteByCpf(Long cpf);
}
