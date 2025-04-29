package com.xpe.desaffio_final.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpe.desaffio_final.ecommerce.dto.CategoriaDTO;
import com.xpe.desaffio_final.ecommerce.dto.DadosListagemCategoria;
import com.xpe.desaffio_final.ecommerce.modelo.Categoria;
import com.xpe.desaffio_final.ecommerce.repository.CategoriaRepository;
import com.xpe.desaffio_final.ecommerce.service.exceptions.ResourceNotFoundException;
import com.xpe.desaffio_final.ecommerce.vo.RelatorioDeVendasCategoriaVo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	

	/*
	 * public Page<RelatorioDeVendasCategoriaVo> categoriaVendas(Pageable page) {
	 * //Page<Categoria> categorias = repository.findAll(page); return
	 * repository.getRelatorioVendasPorCategoria(page);//
	 * vendas.stream().map(RelatorioDeVendasCategoriaVo::new).toList(); }
	 */
	
	public Page<RelatorioDeVendasCategoriaVo> categoriaVendas(PageRequest pageRequest) {
		Page<RelatorioDeVendasCategoriaVo> vendas = repository.getRelatorioVendasPorCategoria(pageRequest);
		System.out.println(vendas);
		return repository.getRelatorioVendasPorCategoria(pageRequest);
	}
	
	
	public CategoriaDTO buscaCategoriaPorId(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new CategoriaDTO(entity);
	}
	
	public <Optinal>Categoria buscaPorId(Long categoriaId) {
		Optional<Categoria> obj = repository.findById(categoriaId);
		Categoria entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return entity;
	}

	public Page<DadosListagemCategoria> findAllPaged(PageRequest pageRequest) {
		Page<Categoria> list = repository.findAll(pageRequest);
		return list.map(x -> new DadosListagemCategoria(x));
	}
	
	public CategoriaDTO insert(CategoriaDTO dto) {
		repository.save(new Categoria(dto));
		return dto;
	}

	public CategoriaDTO update(Long categoriaId, CategoriaDTO dto) {
		try {
			Categoria entity = repository.getReferenceById(categoriaId);
			entity = repository.save(new Categoria(dto));
			return new CategoriaDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + categoriaId);
		}
	}

	/*
	 * public void delete(Long categoriaId) { try {
	 * repository.deleteById(categoriaId); } catch (EmptyResultDataAccessException
	 * e) { throw new ResourceNotFoundException("Id not found " + categoriaId); }
	 * catch (DataIntegrityViolationException e) { throw new
	 * DatabaseException("Integrity violation"); } }
	 */
	
	public void delete(Long categoriaId) {
		var categoria = repository.getReferenceById(categoriaId);
		categoria.excluir();
	}
}
