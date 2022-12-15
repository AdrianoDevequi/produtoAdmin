package com.admin.spring.web.controllers.viewControllers.adminControllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.spring.api.empresa.EmpresaRepository;
import com.admin.spring.api.endereco.DadosAtualizarEndereco;
import com.admin.spring.api.endereco.DadosCadastroEndereco;
import com.admin.spring.api.endereco.Endereco;
import com.admin.spring.api.endereco.EnderecoRepository;
import com.admin.spring.api.empresa.DadosAtualizarEmpresa;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.empresa.DadosListagemEmpresa;
import com.admin.spring.api.empresa.Empresa;


@Controller
@RequestMapping("empresas")
public class EmpresaController {
	
	
	
	@Autowired
	private EmpresaRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-empresa", method = RequestMethod.GET)
		public ModelAndView novaEmpresaForm() {
			ModelAndView mav = new ModelAndView("empresa/cadastrar-empresa");
			mav.addObject("novaEmpresa", new Empresa());
			mav.addObject("novoEndereco", new Endereco());

			return mav;
		}
		// Salva dados formulário
		
		@RequestMapping(value = "/cadastrar-empresa", method = RequestMethod.POST)
    public ModelAndView novaEmpresa(
							  @ModelAttribute("novaEmpresa") @Valid DadosCadastroEmpresa novaEmpresa,
							  @ModelAttribute("novoEndereco") @Valid DadosCadastroEndereco novoEndereco,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
							  Empresa empresa,
							  Endereco endereco,
							  HttpServletResponse httpResponse,
							  Errors errors,
							  ModelAndView modelAndView
							  ) {
		

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/empresa/cadastrar-empresa");
		}else{

			//enderecoRepository.save(new Endereco(novoEndereco));
			novaEmpresa.setEndereco(novoEndereco);
			repository.save(new Empresa(novaEmpresa));
			
			
			
			redirectAttributes.addFlashAttribute("fornecedorSalvo", true);

			modelAndView.setViewName("redirect:/empresas/lista-empresas");
		}

        return modelAndView;
    }


	// Manda Informações para view
	@RequestMapping(value = "/lista-empresas", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("empresa/lista-empresas");
		List<Empresa> empresa = repository.findAll();
		mav.addObject("empresas", empresa);
		return mav;
	}
	
	@RequestMapping(value = "/editar-empresa/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id, ModelAndView modelAndView) {
		modelAndView.setViewName("empresa/editar-empresa");

		Empresa atualizarEmpresa = repository.findById(id).get();
		Endereco enderecoAtualizar = atualizarEmpresa.getEndereco();
		
		modelAndView.addObject("atualizarEmpresa", atualizarEmpresa);
		modelAndView.addObject("end", enderecoAtualizar);

		return modelAndView;
	}

	@RequestMapping(value = "/editar-empresa/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
			@ModelAttribute("atualizarEmpresa") @Valid DadosAtualizarEmpresa atualizarEmpresa, 
			@ModelAttribute("end") @Valid DadosAtualizarEndereco atualizarEndereco, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("empresa/editar-empresa");
			}else{
				
			var empresa= repository.findById(id).get();
			
			atualizarEndereco.setId(atualizarEndereco.getId());
			atualizarEmpresa.setEndereco(atualizarEndereco);
			
			empresa.atualizarInformacoes(atualizarEmpresa);
			modelAndView.setViewName("redirect:/empresas/lista-empresas");
			redirectAttributes.addFlashAttribute("empresaAtualizada", true);
			}


		
		return modelAndView;
	}
	/* 
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
	}
*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Empresa> remove(@PathVariable String id) {
		repository.deleteById(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}
}
