package com.marcelo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.api.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);

}
