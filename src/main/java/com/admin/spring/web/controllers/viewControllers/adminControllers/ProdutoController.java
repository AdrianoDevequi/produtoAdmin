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

import com.admin.spring.api.categoria.Categoria;
import com.admin.spring.api.categoria.CategoriaRepository;
import com.admin.spring.api.categoria.DadosCadastroCategoria;
import com.admin.spring.api.empresa.DadosCadastroEmpresa;
import com.admin.spring.api.empresa.Empresa;
import com.admin.spring.api.empresa.EmpresaRepository;
import com.admin.spring.api.endereco.DadosCadastroEndereco;
import com.admin.spring.api.fornecedor.DadosCadastroFornecedor;
import com.admin.spring.api.fornecedor.Fornecedor;
import com.admin.spring.api.fornecedor.FornecedorRepository;
import com.admin.spring.api.produto.DadosAtualizarProduto;
import com.admin.spring.api.produto.DadosCadastroProduto;
import com.admin.spring.api.produto.DadosListagemProduto;
import com.admin.spring.api.produto.Produto;
import com.admin.spring.api.produto.ProdutoRepository;



@Controller
@RequestMapping("produtos")
public class ProdutoController {
	
	
	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

		// Manda Informações para view
		@RequestMapping(value = "/cadastrar-produto", method = RequestMethod.GET)
		public ModelAndView novoProdutoForm() {
			ModelAndView mav = new ModelAndView("produto/cadastrar-produto");

			List<Fornecedor> fornecedores = fornecedorRepository.findAll();
			List<Categoria> categorias = categoriaRepository.findAll();
			List<Empresa> empresas = empresaRepository.findAll();

			mav.addObject("fornecedores", fornecedores);
			mav.addObject("categorias", categorias);
			mav.addObject("empresas", empresas);
			
			mav.addObject("novoProduto", new Produto());

			return mav;
		}

		// Salva dados formulário
		@RequestMapping(value = "/cadastrar-produto", method = RequestMethod.POST)
    public ModelAndView novoProduto(
							  @ModelAttribute("novoProduto") @Valid DadosCadastroProduto novoProduto,					  		  
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes,
							  Produto produto,
							  HttpServletResponse httpResponse,
							  Errors errors,
							  ModelAndView modelAndView
							  ) {
		String meuLog = ("iniciando");
		meuLog += (novoProduto.toString());
		meuLog += ("finalizando");

		logJava(meuLog);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/produto/cadastrar-produto");
		}else{

			novoProduto.setCategoria(new Categoria(Long.parseLong(novoProduto.getCategoriaid())));

			var empresa= empresaRepository.findById(Long.parseLong(novoProduto.getEmpresaid())).get();
			
			DadosCadastroEndereco ende = new DadosCadastroEndereco(empresa.getEndereco().getId());
			
			novoProduto.setEmpresa(new DadosCadastroEmpresa(empresa.getId(), ende));
			
			novoProduto.setFornecedor(new DadosCadastroFornecedor(Long.parseLong(novoProduto.getFornecedorid())));
			
			repository.save(new Produto(novoProduto));


			redirectAttributes.addFlashAttribute("produtoSalvo", true);

			modelAndView.setViewName("redirect:/produtos/lista-produtos");
		}

        return modelAndView;
    }


	// Manda Informações para view
	@RequestMapping(value = "/lista-produtos", method = RequestMethod.GET)
	public ModelAndView enviaDadosFront() {
		ModelAndView mav = new ModelAndView("produto/lista-produtos");
		List<Produto> produto = repository.findAll();
		mav.addObject("produtos", produto);
		return mav;
	}
	
	@RequestMapping(value = "/editar-produto/{id}", method = RequestMethod.GET)
	public ModelAndView atualizarId(@PathVariable Long id, ModelAndView modelAndView) {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		List<Categoria> categorias = categoriaRepository.findAll();
		List<Empresa> empresas = empresaRepository.findAll();

		modelAndView.addObject("fornecedores", fornecedores);
		modelAndView.addObject("categorias", categorias);
		modelAndView.addObject("empresas", empresas);

		modelAndView.setViewName("produto/editar-produto");

		Produto produtoAtualizar = repository.findById(id).get();

		modelAndView.addObject("atualizarProduto", produtoAtualizar);
		modelAndView.addObject("categoria_sel", produtoAtualizar.getCategoria());
		modelAndView.addObject("empresa_sel", produtoAtualizar.getEmpresa());
		modelAndView.addObject("fornecedor_sel", produtoAtualizar.getFornecedor());


		return modelAndView;
	}

	@RequestMapping(value = "/editar-produto/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView atualizar(
		@ModelAttribute("atualizarProduto") @Valid DadosAtualizarProduto atualizarProduto, 
			BindingResult bindingResult,	
			@PathVariable Long id, 
			RedirectAttributes redirectAttributes,
			Errors errors,
			ModelAndView modelAndView,
			Model model,
			HttpServletResponse httpResponse) {

			if(bindingResult.hasErrors()) {
				modelAndView.setViewName("produto/editar-produto");
			}else{

			var produto= repository.findById(id).get();

			produto.setCategoria(categoriaRepository.findById(Long.parseLong(atualizarProduto.categoriaid())).get());

			produto.setEmpresa(empresaRepository.findById(Long.parseLong(atualizarProduto.empresaid())).get());

			produto.setFornecedor(fornecedorRepository.findById(Long.parseLong(atualizarProduto.fornecedorid())).get());

			produto.atualizarInformacoes(atualizarProduto);

			modelAndView.setViewName("redirect:/produtos/lista-produtos");

			redirectAttributes.addFlashAttribute("produtoAtualizado", true);
			
			}
		
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Empresa> remove(@PathVariable String id) {
		repository.deleteById(Long.valueOf(id));
		return ResponseEntity.noContent().build();
	}

	private static void logJava(String data) {
        try {
            Files.write(Paths.get("C:\\temp\\logjava.txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}