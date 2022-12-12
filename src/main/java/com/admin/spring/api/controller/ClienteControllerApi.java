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
import com.admin.spring.api.empresa.EmpresaRepository;
import com.admin.spring.api.endereco.EnderecoRepository;
import com.admin.spring.api.empresa.DadosAtualizarEmpresa;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.empresa.DadosListagemEmpresa;
import com.admin.spring.api.empresa.Empresa;




@RestController
@RequestMapping("empresas-api")
public class ClienteControllerApi {
	
	@Autowired
	private EmpresaRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroEmpresa dados) {
		repository.save(new Empresa(dados));
	}
	
	@GetMapping
	public Page<DadosListagemEmpresa> listarPagina(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemEmpresa::new);
	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarEmpresa dados) {
		var cliente= repository.getReferenceById(dados.getId());
		cliente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
