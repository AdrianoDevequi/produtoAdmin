package com.admin.spring.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.admin.spring.api.produto.DadosAtualizarProduto;
import com.admin.spring.api.produto.DadosCadastroProduto;
import com.admin.spring.api.produto.DadosListagemProduto;
import com.admin.spring.api.produto.Produto;
import com.admin.spring.api.produto.ProdutoRepository;



@RestController
@RequestMapping("produtos-api")
public class ProdutoControllerApi {
	
	@Autowired
	private ProdutoRepository repository;

	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
		repository.save(new Produto(dados));
	}
	
//	@GetMapping
//	public java.util.List<DadosListagemMedico> listar(){
//		return repository.findAll().stream().map(DadosListagemMedico::new).toList();
//	}
	
	@GetMapping
	public Page<DadosListagemProduto> listarPagina(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemProduto::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarProduto dados) {
		var medico= repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
