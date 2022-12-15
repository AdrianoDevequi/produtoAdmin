package com.admin.spring.api.fornecedor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_fornecedor")
@Entity(name = "Fornecedor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fornecedor {

    public Fornecedor(DadosCadastroFornecedor dados) {
    	if(dados.getId() != null) {
    		this.id = dados.getId();
    	}
		this.nome = dados.getNome();
	}
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@NotBlank (message = "O campo nome é obrigatório")
	private String nome;

    
	public void atualizarInformacoes(DadosAtualizarFornecedor dados) {
		
			this.nome = dados.nome();
		
	
	}

}