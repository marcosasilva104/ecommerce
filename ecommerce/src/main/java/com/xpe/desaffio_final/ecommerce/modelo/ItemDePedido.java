package com.xpe.desaffio_final.ecommerce.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
	private BigDecimal precoUnitario = BigDecimal.ZERO;
	
	@Column(nullable = false)
	private Integer quantidade;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal desconto = BigDecimal.ZERO;
	
	@Column(name = "tipo_desconto", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoDescontoItemPedido tipoDescontoItemPedido; //(QUANTIDADE, PROMOCAO ou NENHUM);
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	
	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}
	 
	public ItemDePedido() {
	}
	
	public ItemDePedido(TipoDescontoItemPedido tipoDescontoItemPedido, Integer quantidade, BigDecimal desconto,
			 Pedido pedido, Produto produto) {
		this.precoUnitario = produto.getPreco();
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.tipoDescontoItemPedido = tipoDescontoItemPedido;
		this.pedido = pedido;
		this.produto = produto;
	}

	public ItemDePedido(@Positive Integer quantidade, Produto produto) {
		this.quantidade = quantidade;
		//this.desconto = desconto;
		//this.tipoDescontoItemPedido = TipoDescontoItemPedido;
		this.produto = produto;
	}
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDescontoItemPedido getTipoDescontoItemPedidoo() {
		return tipoDescontoItemPedido;
	}

	public void setTipoDescontoItemPedido(TipoDescontoItemPedido tipoDescontoItemPedido) {
		this.tipoDescontoItemPedido = tipoDescontoItemPedido;
	}
	
}
