package com.marcelo.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.api.modelo.Topic;

public interface TopicsRepository extends JpaRepository<Topic, Long> {

	Page<Topic> findByTitle(String title, Pageable paginacao);
	

}
