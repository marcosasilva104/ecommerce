package com.xpe.desaffio_final.ecommerce.modelo;

import com.xpe.desaffio_final.ecommerce.dto.CategoriaDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 120)
	private String nome;
	private boolean status;

	public Categoria() {

	}
	
	public Categoria(CategoriaDTO dados) {
		this.id = dados.id();
		this.nome = dados.nome();
		this.status = true;
	}

	public Categoria(String nome) throws IllegalArgumentException {
		this.setNome(nome);
		this.status = Boolean.TRUE;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean isStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", status=" + status + "]";
	}

	public void setNome(String nome) {
		if (nome == null) throw new IllegalArgumentException("Nome da categoria não pode ser nulo");
		if (nome == "" || nome.isEmpty()) throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
	
		this.nome = nome;
	}
	
	 public void excluir() {
	        this.status = false;
	    }

}
