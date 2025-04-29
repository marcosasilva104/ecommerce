package com.xpe.desaffio_final.ecommerce.dto;

import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.ItemDePedido;
import com.xpe.desaffio_final.ecommerce.modelo.Produto;
import com.xpe.desaffio_final.ecommerce.service.ProdutoService;

import jakarta.validation.constraints.Positive;

public record DadosDeProduto(
		@NotNull
		Long id, 
		@Positive
		Integer quantidade 
		) {

	public ItemDePedido converter(ProdutoService produtoService) {
		Optional<Produto> produto = produtoService.findById(id);
		
		System.out.println(produto.toString());
		
		if(produto.isPresent()) 
			return new ItemDePedido(quantidade, produto.get()); 
			
		 return null;
		
	}

}
