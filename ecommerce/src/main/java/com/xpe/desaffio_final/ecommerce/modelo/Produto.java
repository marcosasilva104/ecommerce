package com.xpe.desaffio_final.ecommerce.modelo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", length = 200)
	private String nome;
	
	private String descricao;
	
	@Column(name = "preco", nullable = false)
	private BigDecimal preco = BigDecimal.ZERO;
	
	@Column(name = "quantidade_estoque", nullable = false)
	private Integer quantidadeEstoque;
	
	//private Long categoriaId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id",  nullable = false)
	private Categoria categoria;
	
	public Produto() {}
	
	public Produto(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}
	

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public void setPreco(BigDecimal preco) {
		if (preco.compareTo( new BigDecimal("0.00")) <= 0) {
            throw new IllegalArgumentException("O preço não pode ser menor ou igual a 0.");
        }
		
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
}
