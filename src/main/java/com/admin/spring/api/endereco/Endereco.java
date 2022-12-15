package com.admin.spring.api.endereco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "tb_endereco")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {

    public Endereco(DadosCadastroEndereco dados) {
 		if(dados.getId() != null) {
    		this.id = dados.getId();
    	} 
		
		this.logradouro = dados.getLogradouro();
		this.bairro = dados.getBairro();
		this.cep = dados.getCep();
		this.uf = dados.getUf();
		this.numero = dados.getNumero();
		this.complemento = dados.getComplemento();
		this.cidade = dados.getCidade(); 
	}
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    
	public void atualizarInformacoes(DadosAtualizarEndereco dados) {
		if(dados.getId() != null) {
    		this.id = dados.getId();
    	} 
		if(dados.getLogradouro() != null) {
			this.logradouro = dados.getLogradouro();
		}

		
	}

}