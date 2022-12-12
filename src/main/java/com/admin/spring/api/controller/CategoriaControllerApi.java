package com.admin.spring.api.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.categoria.CategoriaRepository;
import com.admin.spring.api.categoria.DadosAtualizarCategoria;
import com.admin.spring.api.categoria.DadosCadastroCategoria;
import com.admin.spring.api.categoria.DadosListagemCategoria;


@RestController
@RequestMapping("categorias-api")
public class CategoriaControllerApi {
	
	@Autowired
	private CategoriaRepository repository;
	
	@PostMapping
	@Transactional
	@ResponseStatus(value = HttpStatus.ACCEPTED, reason="Cadastrado com sucesso!")
	public void cadastrar(@RequestBody @Valid DadosCadastroCategoria dados) {
		repository.save(new Categoria(dados));
		System.out.println("Cadastrado com sucesso!");

	}
	
	@GetMapping
	public Page<DadosListagemCategoria> listarPagina(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemCategoria::new);
	}

	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarCategoria dados) {
		var fornecedor= repository.getReferenceById(dados.id());
		fornecedor.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}

	
}
