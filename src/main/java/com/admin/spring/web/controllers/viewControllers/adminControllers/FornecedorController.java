package com.admin.spring.web.controllers.viewControllers.adminControllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.fornecedor.DadosAtualizarFornecedor;
import com.admin.spring.api.fornecedor.DadosCadastroFornecedor;
import com.admin.spring.api.fornecedor.Fornecedor;
import com.admin.spring.api.fornecedor.FornecedorRepository;


@Controller
@RequestMapping("fornecedores")
public class FornecedorController {
	
	
	@Autowired
	private FornecedorRepository repository;

		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-fornecedor", method = RequestMethod.GET)
		public ModelAndView novoFornecedorForm() {
			ModelAndView mav = new ModelAndView("fornecedor/cadastrar-fornecedor");
			mav.addObject("novoFornecedor", new Fornecedor());

			return mav;
		}
		// Salva dados formulário
		@RequestMapping(value = "/cadastrar-fornecedor", method = RequestMethod.POST)
    public ModelAndView novoFornecedor(
							  @ModelAttribute("novoFornecedor") @Valid DadosCadastroFornecedor novoFornecedor,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
							  Fornecedor fornecedor,
							  HttpServletResponse httpResponse,
							  Errors errors,
							  ModelAndView modelAndView
							  ) {

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/fornecedor/cadastrar-fornecedor");
		}else{
			repository.save(new Fornecedor(novoFornecedor));
			redirectAttributes.addFlashAttribute("fornecedorSalvo", true);

			modelAndView.setViewName("redirect:/fornecedores/lista-fornecedores");
		}

        return modelAndView;
    }


	// Manda Informações para view
	@RequestMapping(value = "/lista-fornecedores", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("fornecedor/lista-fornecedores");
		List<Fornecedor> fornecedor = repository.findAll();
		mav.addObject("fornecedores", fornecedor);
		return mav;
	}
	
	@RequestMapping(value = "/editar-fornecedor/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("fornecedor/editar-fornecedor");
		Fornecedor fornecedorAtualizar = repository.findById(id).get();
		mav.addObject("atualizarFornecedor", fornecedorAtualizar);

		return mav;
	}

	@RequestMapping(value = "/editar-fornecedor/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
		@ModelAttribute("atualizarFornecedor") @Valid DadosAtualizarFornecedor atualizarFornecedor, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("fornecedor/editar-fornecedor");
			}else{

			var fornecedor= repository.findById(id).get();
			fornecedor.atualizarInformacoes(atualizarFornecedor);
			modelAndView.setViewName("redirect:/fornecedores/lista-fornecedores");
			redirectAttributes.addFlashAttribute("fornecedorAtualizado", true);
			}


		
		return modelAndView;
	}
	
	/* 
	@DeleteMapping(path = "/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> remove(@PathVariable String id) {
		repository.deleteById(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
	
}