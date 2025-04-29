package com.xpe.desaffio_final.ecommerce.vo;

import java.math.BigDecimal;

public class RelatorioDeVendasClienteVo {

	private String nomeCliente;
	private Long quantidadeVendida;
	private BigDecimal montanteVendido;
	
	public RelatorioDeVendasClienteVo(String nomeCliente, Long quantidadeVendida, BigDecimal montanteVendido) {
		this.nomeCliente = nomeCliente;
		this.quantidadeVendida = quantidadeVendida;
		this.montanteVendido = montanteVendido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public BigDecimal getMontanteVendido() {
		return montanteVendido;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasCategoriaVo [nomeCategria=" + nomeCliente + ", quantidadeVendida=" + quantidadeVendida
				+ ", montanteVendido=" + montanteVendido + "]";
	}

	
}
