package com.admin.spring.api.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DadosAtualizarCategoria(
		@NotNull
		Long id, 
		@NotBlank(message = "O campo nome é obrigatório")
		String nome) {

}