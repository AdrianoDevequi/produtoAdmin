package com.admin.spring.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.spring.api.fornecedor.DadosAtualizarFornecedor;
import com.admin.spring.api.fornecedor.DadosCadastroFornecedor;
import com.admin.spring.api.fornecedor.DadosListagemFornecedor;
import com.admin.spring.api.fornecedor.Fornecedor;
import com.admin.spring.api.fornecedor.FornecedorRepository;
import com.admin.spring.service.UserService;


@RestController
@RequestMapping("fornecedores-api")
public class FornecedorControllerApi {
	
	
	@Autowired
	private FornecedorRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroFornecedor dados) {
		repository.save(new Fornecedor(dados));
	}
	
	@GetMapping
	public Page<DadosListagemFornecedor> listarPagina(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemFornecedor::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarFornecedor dados) {
		var fornecedor= repository.getReferenceById(dados.id());
		fornecedor.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
