package com.admin.spring.api.categoria;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class DadosCadastroCategoria{
	DadosCadastroCategoria(
		Long id,
		@NotBlank
		String nome) 
		{}

		private Long id;
		private String nome;


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

	public DadosCadastroCategoria id(Long id) {
		setId(id);
		return this;
	}

	public DadosCadastroCategoria nome(String nome) {
		setNome(nome);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof DadosCadastroCategoria)) {
			return false;
		}
		DadosCadastroCategoria dadosCadastroCategoria = (DadosCadastroCategoria) o;
		return Objects.equals(id, dadosCadastroCategoria.id) && Objects.equals(nome, dadosCadastroCategoria.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			"}";
	}

}
