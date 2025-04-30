package com.xpe.desaffio_final.ecommerce.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
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

import com.xpe.desaffio_final.ecommerce.dto.CategoriaDTO;
import com.xpe.desaffio_final.ecommerce.dto.DadosListagemCategoria;
import com.xpe.desaffio_final.ecommerce.service.CategoriaService;
import com.xpe.desaffio_final.ecommerce.vo.RelatorioDeVendasCategoriaVo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categorias")
@SecurityRequirement(name = "bearer-key")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	// @PostMapping
	// public void cadastrar(@RequestBody Categoria categoria) {
	// service.inseri(categoria);
	// }

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDTO> inserir(@RequestBody @Valid CategoriaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> busacarPorId(@PathVariable("id") Long categoriaId) {
		CategoriaDTO dto = service.buscaCategoriaPorId(categoriaId);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<CategoriaDTO> update(@PathVariable("id") Long categoriaId, @RequestBody @Valid CategoriaDTO dto) {
		dto = service.update(categoriaId, dto);
		return ResponseEntity.ok().body(dto);
	}


	@GetMapping("/vendas")
	public ResponseEntity<Page<RelatorioDeVendasCategoriaVo>> relatorioVendasCategoria(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "categoria.nome"
			) String orderBy
			) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<RelatorioDeVendasCategoriaVo> list = service.categoriaVendas(pageRequest);

		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemCategoria>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<DadosListagemCategoria> list = service.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		service.delete(id);
		
		return ResponseEntity.notFound().build();
	}

}
