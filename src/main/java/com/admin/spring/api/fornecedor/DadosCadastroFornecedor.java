package com.admin.spring.api.fornecedor;

import javax.validation.constraints.NotBlank;

public class DadosCadastroFornecedor {

	private Long id;
	@NotBlank(message = "O campo nome é obrigatório")
	private String nome;

	public DadosCadastroFornecedor() {
	}

	public DadosCadastroFornecedor(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public DadosCadastroFornecedor(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DadosCadastroFornecedor id(Long id) {
		setId(id);
		return this;
	}

	public DadosCadastroFornecedor nome(String nome) {
		setNome(nome);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", nome='" + getNome() + "'" +
				"}";
	}

}