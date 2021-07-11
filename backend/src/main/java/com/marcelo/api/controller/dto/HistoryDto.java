package com.marcelo.api.controller.dto;

import java.time.LocalDateTime;

import com.marcelo.api.modelo.History;

public class HistoryDto {
	private Long id;
	private String mesage;
	private LocalDateTime dateCreated;
	private String nameAuthor;
	
	public HistoryDto(History history) {
		this.id = history.getId();
		this.mesage = history.getMesage();
		this.dateCreated = history.getDateCreated();
		this.nameAuthor = history.getAuthor().getName();
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

	
}
