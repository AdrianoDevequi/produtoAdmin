package com.admin.spring.api.produto;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.fornecedor.Fornecedor;

public record DadosListagemProduto(
		Long id,
		String nome,
		String descricao,
		String preco,
		Categoria categoria,
		Fornecedor fornecedor) {
	
	public DadosListagemProduto(Produto produto) {
		this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCategoria(), produto.getFornecedor());
	}
	
}
