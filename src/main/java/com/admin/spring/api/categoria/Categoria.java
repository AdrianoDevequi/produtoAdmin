package com.admin.spring.api.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "tb_categoria")
@Entity(name = "Categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    public Categoria(DadosCadastroCategoria dados) {
    	if(dados.getId() != null) {
    		this.id = dados.getId();
    	}
		this.nome = dados.getNome();
	}
    
	public Categoria(Long id){
		this.id = id;
	}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;

    
	public void atualizarInformacoes(DadosAtualizarCategoria dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
	
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", nome='" + getNome() + "'" +
			"}";
	}


}