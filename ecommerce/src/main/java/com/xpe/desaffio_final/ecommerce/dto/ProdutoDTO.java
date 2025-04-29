package com.xpe.desaffio_final.ecommerce.dto;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(
		@NotBlank(message = "nome.obrigatorio")
		String nome, 
		
		@NotNull(message = "{descricao.obrigatorio}")
		String descricao,
		
		BigDecimal preco, 
		
		Integer quantidadeEstoque,
		
		Categoria categoria) {

	public ProdutoDTO(Produto entity) {
		this(entity.getNome(), entity.getDescricao(), entity.getPreco(), entity.getQuantidadeEstoque(), entity.getCategoria());
	}

}
