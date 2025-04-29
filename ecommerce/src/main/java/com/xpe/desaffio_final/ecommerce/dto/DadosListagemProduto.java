package com.xpe.desaffio_final.ecommerce.dto;

import java.math.BigDecimal;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;

public record DadosListagemProduto(String nome, BigDecimal preco, String descricao, Integer quantidadeEstoque, Categoria categoria) {

	
	  public DadosListagemProduto(Produto dados) { 
		  this(dados.getNome(), dados.getPreco(), dados.getDescricao(), dados.getQuantidadeEstoque(), dados.getCategoria()); 
		  }
	 

}
