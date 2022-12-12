package com.admin.spring.api.categoria;

import javax.validation.constraints.NotNull;

public record DadosAtualizarCategoria(
		@NotNull
		Long id, 
		String nome) {

}
