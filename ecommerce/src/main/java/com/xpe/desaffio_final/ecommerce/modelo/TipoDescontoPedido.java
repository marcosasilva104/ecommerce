package com.xpe.desaffio_final.ecommerce.modelo;

import java.math.BigDecimal;

public enum TipoDescontoPedido {

	FIDELIDADE(new BigDecimal("0.05")), 
	NENHUM(BigDecimal.ZERO),
	QUANTIDADE(new BigDecimal("0.10"));
	
	TipoDescontoPedido(BigDecimal percentualDesconto) {
		this.desconto = percentualDesconto;
	}
	
	private BigDecimal desconto;

	public BigDecimal getDesconto() {
		return desconto;
	}
	
	BigDecimal getTotalDesconto(BigDecimal total) {
		return total.multiply(getDesconto());
	}
	
	public static void main(String[] args) {
		System.out.println(TipoDescontoPedido.FIDELIDADE
				.getTotalDesconto(new BigDecimal("1000")));
	}
}
