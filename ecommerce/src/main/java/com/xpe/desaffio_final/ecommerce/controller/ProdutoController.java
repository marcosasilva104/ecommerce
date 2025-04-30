package com.xpe.desaffio_final.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.xpe.desaffio_final.ecommerce.dto.ProdutoDTO;
import com.xpe.desaffio_final.ecommerce.form.ProdutoForm;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;
import com.xpe.desaffio_final.ecommerce.service.CategoriaService;
import com.xpe.desaffio_final.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/produtos")
@SecurityRequirement(name = "bearer-key")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,					
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<ProdutoDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable("id") Long produtoId) {
		Optional<Produto> prouduto = service.findById(produtoId);
		return ResponseEntity.ok().body(prouduto);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Object> insertProduto(@RequestBody @Valid ProdutoForm form,
												UriComponentsBuilder uriBuilder,
												BindingResult result ){
		
		try {
			if(result.hasErrors()) 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			Produto novo = form.converter(categoriaService);
			service.insert(novo);			
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(form).toUri();
			return ResponseEntity.created(uri).body(form);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu uma falha no cadastro do produto");
		}
		
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Object> update(@PathVariable("id") Long ProdutoId, @RequestBody ProdutoForm form){
		service.update(ProdutoId, form);
		return ResponseEntity.ok().body(form);
	}
	
	@DeleteMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


}
