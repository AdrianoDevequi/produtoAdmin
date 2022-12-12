package com.admin.spring.api.produto;

import javax.validation.constraints.NotNull;

import com.admin.spring.api.categoria.DadosCadastroCategoria;

public record DadosAtualizarProduto(
		@NotNull
		Long id, 
		String nome, 
		String descricao, 
		String preco,
		String categoriaid,
		String fornecedorid,
		String empresaid,
		DadosCadastroCategoria categoria ) {

}
