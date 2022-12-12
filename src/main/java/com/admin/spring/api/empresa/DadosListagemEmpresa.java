package com.admin.spring.api.empresa;

import com.admin.spring.api.endereco.Endereco;


public record DadosListagemEmpresa(		
		Long id,
		String nome,
		String email,
		String telefone,
		Endereco endereco) {
	
	public DadosListagemEmpresa(Empresa empresa) {
		this(empresa.getId(), empresa.getNome(), empresa.getEmail(), empresa.getTelefone(), empresa.getEndereco());
	}
}