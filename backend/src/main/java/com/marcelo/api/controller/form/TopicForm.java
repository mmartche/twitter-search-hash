package com.marcelo.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.marcelo.api.modelo.Topic;
import com.marcelo.api.modelo.User;

public class TopicForm {
	
	@NotNull @NotEmpty
	private String title;
	private String mesage;
	private User author;
	
	public Topic converter() {
		return new Topic(getTitle(), getMesage());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
}
