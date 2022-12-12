package com.admin.spring.api.fornecedor;

public record DadosListagemFornecedor(
		Long id,
		String nome) {
	
	public DadosListagemFornecedor(Fornecedor fornecedor) {
		this(fornecedor.getId(), fornecedor.getNome());
	}
}
