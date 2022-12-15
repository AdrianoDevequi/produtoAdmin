package com.admin.spring.api.empresa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.admin.spring.api.endereco.Endereco;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_empresa")
@Entity(name = "Empresa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {

    public Empresa(DadosCadastroEmpresa dados) {
		if(dados.getId() != null) {
    		this.id = dados.getId();
    	} 
		this.nome = dados.getNome();
		this.email = dados.getEmail();
		this.telefone = dados.getTelefone();
		
		this.endereco = new Endereco(dados.getEndereco());
	}
    
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	private String email;
	private String telefone;

    
	public void atualizarInformacoes(DadosAtualizarEmpresa dados) {
			this.nome = dados.getNome();
			this.email = dados.getEmail();
			this.telefone = dados.getTelefone();
			
			this.endereco.atualizarInformacoes(dados.getEndereco());

	}

}