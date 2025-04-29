package br.com.alura.unicommerce.form;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import br.com.alura.unicommerce.modelo.Produto;
import br.com.alura.unicommerce.service.CategoriaService;
import jakarta.validation.constraints.Positive;

public class ProdutoForm {
	
	@Length(min = 2)
	private String nome;
	
	private String descricao;
	
	@Positive
	private BigDecimal preco;
	
	private Integer quantidadeEstoque;
	
	private Long categoriaId;
	
	public ProdutoForm() {}
	
	public ProdutoForm(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Long categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoriaId = categoriaId;
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
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Produto converter(CategoriaService categoriaService) {
		return new Produto(nome, descricao, preco, quantidadeEstoque, categoriaService.buscaPorId(categoriaId));
	}
	

	@Override
	public String toString() {
		return "ProdutoForm [nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", quantidadeEstoque="
				+ quantidadeEstoque + ", categoriaId=" + categoriaId + "]";
	}

	
}
