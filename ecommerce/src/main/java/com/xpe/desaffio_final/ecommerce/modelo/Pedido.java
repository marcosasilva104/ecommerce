package com.xpe.desaffio_final.ecommerce.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private BigDecimal desconto = BigDecimal.ZERO;
	
	@Column(name = "tipo_desconto")
	private TipoDescontoPedido tipoDescontoPedido; 

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
	private List<ItemDePedido> itemPedidos = new ArrayList<>();
	
	@Transient
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	public List<ItemDePedido> getItemPedidos() {
		return itemPedidos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public BigDecimal getDescontosDeitemPedidos() {
		return descontosDeitemPedidos;
	}

	@Transient
	private BigDecimal descontosDeitemPedidos;
	
	@Transient
	private Long quantidadeDeItens;
	
	public Pedido() {
	}

	public Pedido(BigDecimal desconto, TipoDescontoPedido tipoDescontoPedido, Cliente cliente) {
		this.desconto = desconto;
		this.tipoDescontoPedido = tipoDescontoPedido;
		this.cliente = cliente;
	}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	public Pedido(Cliente cliente,  List<ItemDePedido> itemPedidos) {
		//this.desconto = desconto;
		//this.tipoDescontoPedido = tipoDescontoPedido;
		this.cliente = cliente;
		//this.itemPedidos = itemPedidos.stream().map(item -> adicionarItem(item));
		adicionarItemPedidos(itemPedidos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public TipoDescontoPedido getTipoDescontoPedido() {
		return tipoDescontoPedido;
	}

	public void setTipoDesconto(TipoDescontoPedido tipoDescontoPedido) {
		this.tipoDescontoPedido = tipoDescontoPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void adicionarItem(ItemDePedido item) {
	     item.setPedido(this);
	     this.itemPedidos.add(item);
	     this.quantidadeDeItens = this.quantidadeDeItens + item.getQuantidade();
	     this.descontosDeitemPedidos = this.descontosDeitemPedidos.add(item.getDesconto());
	     this.valorTotal = this.valorTotal.add(item.getValor());
	}
	
	public void adicionarItemPedidos(List<ItemDePedido> itemPedido) {
		itemPedidos.forEach(item -> adicionarItem(item));;
	}

	/*
	 * public BigDecimal getPreco() { return preco; }
	 * 
	 * 
	 * public void setPreco(BigDecimal preco) { this.preco = preco; }
	 */

	/*
	 * public int getQuantidade() { return quantidade; }
	 * 
	 * 
	 * public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
	 */
	
}
