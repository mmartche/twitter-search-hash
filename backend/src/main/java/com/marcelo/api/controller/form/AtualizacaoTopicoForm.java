package com.marcelo.api.controller.form;

import javax.validation.constraints.NotNull;

import com.marcelo.api.modelo.Topico;
import com.marcelo.api.repository.TopicoRepository;

public class AtualizacaoTopicoForm {
	@NotNull
	private String titulo;
	private String mensagem;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
	
}
