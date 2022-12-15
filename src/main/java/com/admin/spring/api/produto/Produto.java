package com.admin.spring.api.produto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.fornecedor.Fornecedor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "tb_produto")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    public Produto(DadosCadastroProduto dados) {
		this.nome = dados.getNome();
		this.descricao = dados.getDescricao();
		this.preco = dados.getPreco();

		this.categoria = dados.getCategoria(); 
		
		this.fornecedor = new Fornecedor(dados.getFornecedor());

		this.empresa = new Empresa(dados.getEmpresa());
		
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String preco;
    
    
	@ManyToOne(cascade=CascadeType.MERGE)
	private Categoria categoria;

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Fornecedor fornecedor;

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@ManyToOne(cascade=CascadeType.MERGE)
	private Empresa empresa;

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void atualizarInformacoes(@Valid DadosAtualizarProduto dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		if(dados.preco() != null) {
			this.preco = dados.preco();
		}

	}

}