package com.marcelo.api.controller.dto;

import java.util.List;

import com.marcelo.api.modelo.Tweet;

import twitter4j.Status;

public class TwitterResultDto {
	
	private String objJson;

	public TwitterResultDto(List<Status> list) {
		// TODO Auto-generated constructor stub
		this.objJson = ((Tweet) list).getObjJson();
	}

	public String getObjJson() {
		return objJson;
	}

	public void setObjJson(String objJson) {
		this.objJson = objJson;
	}

}
