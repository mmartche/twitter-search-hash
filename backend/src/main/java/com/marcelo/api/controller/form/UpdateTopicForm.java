package com.marcelo.api.controller.form;

import javax.validation.constraints.NotNull;

import com.marcelo.api.modelo.Topic;
import com.marcelo.api.repository.TopicsRepository;

public class UpdateTopicForm {
	@NotNull
	private String title;
	private String mesage;

	public String getTitle() {
		return title;
	}

	public void setTittle(String title) {
		this.title = title;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public Topic update(Long id, TopicsRepository topicsRepository) {
		Topic topic = topicsRepository.getById(id);
		topic.setTitle(this.title);
		topic.setMesage(this.mesage);
		return topic;
	}

}
