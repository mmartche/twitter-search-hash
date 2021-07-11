package com.marcelo.api.config.validacao;

public class ErrorFormDto {

	private String field;
	private String erro;
	public ErrorFormDto(String field, String erro) {
		super();
		this.field = field;
		this.erro = erro;
	}
	public String getErro() {
		return erro;
	}
	public String getField() {
		return field;
	}
	
	
}
