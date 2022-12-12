package com.admin.spring.api.fornecedor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record DadosAtualizarFornecedor(
		@NotNull
		Long id,
		@NotBlank(message = "O campo nome é obrigatório")
		String nome) {

}
