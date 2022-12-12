package com.admin.spring.api.empresa;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.admin.spring.api.endereco.DadosEndereco;

public class DadosCadastroEmpresa{

		Long id;
		@NotBlank
		String nome;
		@Email
		String email;
		@NotBlank
		String telefone;
		@Valid
		DadosEndereco endereco;
		


	public DadosCadastroEmpresa() {
	}

	public DadosCadastroEmpresa(Long id, String nome, String email, String telefone, DadosEndereco endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	public DadosCadastroEmpresa(Long id, DadosEndereco endereco) {
		this.id = id;
		this.endereco = endereco;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public DadosEndereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(DadosEndereco endereco) {
		this.endereco = endereco;
	}

	public DadosCadastroEmpresa id(Long id) {
		setId(id);
		return this;
	}

	public DadosCadastroEmpresa nome(String nome) {
		setNome(nome);
		return this;
	}

	public DadosCadastroEmpresa email(String email) {
		setEmail(email);
		return this;
	}

	public DadosCadastroEmpresa telefone(String telefone) {
		setTelefone(telefone);
		return this;
	}

	public DadosCadastroEmpresa endereco(DadosEndereco endereco) {
		setEndereco(endereco);
		return this;
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			", email='" + getEmail() + "'" +
			", telefone='" + getTelefone() + "'" +
			", endereco='" + getEndereco() + "'" +
			"}";
	}


}
