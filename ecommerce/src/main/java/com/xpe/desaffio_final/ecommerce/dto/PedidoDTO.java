package com.xpe.desaffio_final.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.Pedido;

import jakarta.validation.Valid;

public class PedidoDTO{
		
	private Long id;
	
	private LocalDate data;
	
	private BigDecimal valor;
	
    @NotNull @Valid 
    private ClienteDTO cliente;
    
    private Long clienteId;
    
    private String nomeCliente;
    
    
    @NotNull @Valid 
    private ProdutoDTO produto;

	public PedidoDTO(Pedido obj) {
		this.id = obj.getId();
		this.data = obj.getData();
		this.clienteId = cliente.id();
		this.nomeCliente = cliente.nome();
		//this.produto = new ProdutoDTO(obj.getProduto());
	}

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	
}
