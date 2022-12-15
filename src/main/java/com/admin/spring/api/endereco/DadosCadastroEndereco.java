package com.admin.spring.api.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class DadosCadastroEndereco{
		private Long id;
		@NotBlank
		private String logradouro; 
		@NotBlank
		private String bairro; 
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		private String cep; 
		@NotBlank
		private String cidade; 
		@NotBlank
		private String uf; 
		private String complemento; 
		private String numero;


	public DadosCadastroEndereco() {
	}

	public DadosCadastroEndereco(Long id, String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
		this.id = id;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.complemento = complemento;
		this.numero = numero;
	}

	public DadosCadastroEndereco(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public DadosCadastroEndereco id(Long id) {
		setId(id);
		return this;
	}

	public DadosCadastroEndereco logradouro(String logradouro) {
		setLogradouro(logradouro);
		return this;
	}

	public DadosCadastroEndereco bairro(String bairro) {
		setBairro(bairro);
		return this;
	}

	public DadosCadastroEndereco cep(String cep) {
		setCep(cep);
		return this;
	}

	public DadosCadastroEndereco cidade(String cidade) {
		setCidade(cidade);
		return this;
	}

	public DadosCadastroEndereco uf(String uf) {
		setUf(uf);
		return this;
	}

	public DadosCadastroEndereco complemento(String complemento) {
		setComplemento(complemento);
		return this;
	}

	public DadosCadastroEndereco numero(String numero) {
		setNumero(numero);
		return this;
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", logradouro='" + getLogradouro() + "'" +
			", bairro='" + getBairro() + "'" +
			", cep='" + getCep() + "'" +
			", cidade='" + getCidade() + "'" +
			", uf='" + getUf() + "'" +
			", complemento='" + getComplemento() + "'" +
			", numero='" + getNumero() + "'" +
			"}";
	}

	
}