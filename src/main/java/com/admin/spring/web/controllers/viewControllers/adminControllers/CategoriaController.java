package com.admin.spring.web.controllers.viewControllers.adminControllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.categoria.CategoriaRepository;
import com.admin.spring.api.categoria.DadosAtualizarCategoria;
import com.admin.spring.api.categoria.DadosCadastroCategoria;
import com.admin.spring.api.categoria.DadosListagemCategoria;
import com.admin.spring.api.fornecedor.Fornecedor;


@RestController
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-categoria", method = RequestMethod.GET)
		public ModelAndView novoFornecedorForm() {
			ModelAndView mav = new ModelAndView("categoria/cadastrar-categoria");
			mav.addObject("novaCategoria", new Categoria());

			return mav;
		}	
	
	// Salva dados formulário
	@RequestMapping(value = "/cadastrar-categoria", method = RequestMethod.POST)
	public ModelAndView novoFornecedor(
							@ModelAttribute("novaCategoria") @Valid DadosCadastroCategoria novaCategoria,
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes,
							Categoria categoria,
							HttpServletResponse httpResponse,
							Errors errors,
							ModelAndView modelAndView
							) {

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/categoria/cadastrar-categoria");
		}else{
			repository.save(new Categoria(novaCategoria));
			redirectAttributes.addFlashAttribute("categoriaSalva", true);

			modelAndView.setViewName("redirect:/categorias/lista-categorias");
		}

		return modelAndView;
	}
	


	// Manda Informações para view
	@RequestMapping(value = "/lista-categorias", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("categoria/lista-categorias");
		List<Categoria> categoria = repository.findAll();
		mav.addObject("categorias", categoria);
		return mav;
	}


	// Carrega informações para tela de editar
	@RequestMapping(value = "/editar-categoria/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("categoria/editar-categoria");
		Categoria categoriaAtualizar = repository.findById(id).get();
		mav.addObject("atualizarCategoria", categoriaAtualizar);

		return mav;
	}
	// Atualiza
	@RequestMapping(value = "/editar-categoria/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
		@ModelAttribute("atualizarCategoria") @Valid DadosAtualizarCategoria atualizarCategoria, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("categoria/editar-categoria");
			}else{

			var categoria= repository.findById(id).get();
			categoria.atualizarInformacoes(atualizarCategoria);
			modelAndView.setViewName("redirect:/categorias/lista-categorias");
			redirectAttributes.addFlashAttribute("categoriaAtualizada", true);
			}


		
		return modelAndView;
	}

	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarCategoria dados) {
		var categoria= repository.getReferenceById(dados.id());
		categoria.atualizarInformacoes(dados);
	}
	
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}

	
}