package com.xpe.desaffio_final.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xpe.desaffio_final.ecommerce.dto.ProdutoDTO;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;
import com.xpe.desaffio_final.ecommerce.repository.CategoriaRepository;
import com.xpe.desaffio_final.ecommerce.repository.ProdutoRepository;
import com.xpe.desaffio_final.ecommerce.service.exceptions.DatabaseException;
import com.xpe.desaffio_final.ecommerce.service.exceptions.ResourceNotFoundException;

import br.com.alura.unicommerce.form.ProdutoForm;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Page<ProdutoDTO> findAllPaged(PageRequest pageRequest) {
		Page<Produto> list = repository.findAll(pageRequest);
		return list.map(x -> new ProdutoDTO(x));
	}

	public Optional<Produto> findById(Long produtoId) {
		Optional<Produto> obj = repository.findById(produtoId);
		Produto entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return Optional.ofNullable(entity);
	}

	public void insert(Produto produto) {
		if (produto == null) throw new IllegalArgumentException("Falha ao cadastrar cadastrar o produto.");
	        repository.save(produto);	
	}

	public ProdutoForm update(Long produtoId, ProdutoForm form) {
		try {
			Produto entity = repository.getReferenceById(produtoId);
			copyDtoToEntity(form, entity);
			entity = repository.save(entity);
			return new ProdutoForm();
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + produtoId);
		}
	}

	public void delete(Long produtoId) {
		try {
			repository.deleteById(produtoId);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + produtoId);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(ProdutoForm form, Produto entity) {
		
		entity.setNome(form.getNome());
		entity.setDescricao(form.getDescricao());
		entity.setQuantidadeEstoque(form.getQuantidadeEstoque());
		entity.setPreco(form.getPreco());
		
//		entity.getProduto().clear();
//		for (CProdutoDTO catDto : dto.getProduto()) {
//			Produto produto = produtoRepository.getReferenceById(catDto.getId());
//			entity.getProduto().add(produto);
//		}
	}

}
