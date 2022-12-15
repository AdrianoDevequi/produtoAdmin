package com.admin.spring.api.produto;

import javax.validation.constraints.NotBlank;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.fornecedor.DadosCadastroFornecedor;

public class DadosCadastroProduto{
		
	private String nome;
	private String descricao;
	private String preco;
	private Categoria categoria;
	private String categoriaid;
	private String fornecedorid;
	private String empresaid;
	private DadosCadastroFornecedor fornecedor;
	private DadosCadastroEmpresa empresa;

	public String getEmpresaid() {
		return this.empresaid;
	}

	public void setEmpresaid(String empresaid) {
		this.empresaid = empresaid;
	}

	public String getFornecedorid() {
		return this.fornecedorid;
	}

	public void setFornecedorid(String fornecedorid) {
		this.fornecedorid = fornecedorid;
	}

	public String getCategoriaid() {
		return this.categoriaid;
	}

	public void setCategoriaid(String categoriaid) {
		this.categoriaid = categoriaid;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPreco() {
		return this.preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public DadosCadastroFornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(DadosCadastroFornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public DadosCadastroEmpresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(DadosCadastroEmpresa empresa) {
		this.empresa = empresa;
	}

	public DadosCadastroProduto nome(String nome) {
		setNome(nome);
		return this;
	}

	public DadosCadastroProduto descricao(String descricao) {
		setDescricao(descricao);
		return this;
	}

	public DadosCadastroProduto preco(String preco) {
		setPreco(preco);
		return this;
	}

	public DadosCadastroProduto categoria(Categoria categoria) {
		setCategoria(categoria);
		return this;
	}

	public DadosCadastroProduto fornecedor(DadosCadastroFornecedor fornecedor) {
		setFornecedor(fornecedor);
		return this;
	}

	public DadosCadastroProduto empresa(DadosCadastroEmpresa empresa) {
		setEmpresa(empresa);
		return this;
	}


	@Override
	public String toString() {
		return "{" +
			" nome='" + getNome() + "'" +
			", descricao='" + getDescricao() + "'" +
			", preco='" + getPreco() + "'" +
			", categoria='" + getCategoria() + "'" +
			", fornecedor='" + getFornecedor() + "'" +
			", empresa='" + getEmpresa() + "'" +
			", categoriaid='" + getCategoriaid() + "'" +
			", fornecedorid='" + getFornecedorid() + "'" +
			", empresaid='" + getEmpresaid() + "'" +
			"}";
	}

	
}