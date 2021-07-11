package com.marcelo.api.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.marcelo.api.modelo.Topic;

public class TopicDto {

	private Long id;
	private String title;
	private String mesage;
	private LocalDateTime dateCreated;
	
	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.mesage = topic.getMesage();
		this.dateCreated = topic.getDateCreated();
	}
	
	public Long getId() {
		return id;
	}

	public static Page<TopicDto> converter(Page<Topic> topics) {
		return topics.map(TopicDto::new);
	}

	public String getTitle() {
		return title;
	}

	public String getMesage() {
		return mesage;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	
	
}
