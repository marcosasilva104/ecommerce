package com.xpe.desaffio_final.ecommerce.dto;

import java.math.BigDecimal;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;

public record ProdutoCategoriaDTO(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Categoria categoria) {

	public ProdutoCategoriaDTO(Produto entity, Categoria categoria) {
		this(entity.getNome(), entity.getDescricao(), entity.getPreco(), entity.getQuantidadeEstoque(), categoria);
	}

}
