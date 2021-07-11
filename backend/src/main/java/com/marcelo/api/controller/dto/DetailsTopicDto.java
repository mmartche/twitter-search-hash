package com.marcelo.api.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.marcelo.api.modelo.StatusTopic;
import com.marcelo.api.modelo.Topic;

public class DetailsTopicDto {
	private Long id;
	private String title;
	private String mesage;
	private LocalDateTime dateCreated;
	private String nameAuthor;
	private StatusTopic status;
	private List<HistoryDto> answwer;
	
	public DetailsTopicDto(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.mesage = topic.getMesage();
		this.dateCreated = topic.getDateCreated();
		this.nameAuthor = topic.getAuthor().getName();
		this.status = topic.getStatus();
		this.answwer = new ArrayList<>();
		this.answwer.addAll(topic.getAnswer().stream().map(HistoryDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getMesage() {
		return mesage;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public StatusTopic getStatus() {
		return status;
	}

	public List<HistoryDto> getRespostas() {
		return answwer;
	}

	public String getTitle() {
		return title;
	}
}
