package com.xpe.desaffio_final.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.Cliente;
import com.xpe.desaffio_final.ecommerce.modelo.ItemDePedido;
import com.xpe.desaffio_final.ecommerce.modelo.Pedido;

public record DadosDePedido(Long clienteId, 
		@NotNull BigDecimal totalPedido,
		@NotNull List<DadosDeProduto> produtos

) {

	
	public Pedido converter(ClienteService clienteService, ProdutoService produtoService) {
		
		Optional<Cliente> cliente = clienteService.buscaPorId(clienteId);
		List<ItemDePedido> itens = produtos.stream().map(produto -> produto.converter(produtoService)).toList();
		
		System.out.println(cliente);
		
		if (cliente.isPresent()) 
			return new Pedido(cliente.get(), itens);
		
		return null;
	}

}
