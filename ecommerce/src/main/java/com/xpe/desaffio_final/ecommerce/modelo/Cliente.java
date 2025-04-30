package com.xpe.desaffio_final.ecommerce.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.xpe.desaffio_final.ecommerce.dto.ClienteDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 120)
	private String nome;

	// columnDefinition = "CHAR(11)"
	@Column(name = "cpf", length = 14, unique = true, nullable = false)
	private String cpf;

	@Column(name = "telefone", length = 14)
	private String telefone;

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "cliente", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Pedido> pedidos = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

	public Cliente() {

	}

	public Cliente(String nome, String cpf, String telefone, Endereco endereco, Usuario usuario) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.endereco = endereco;
		this.usuario = usuario;
	}

	/*
	 * public Cliente(ClienteDTO dados) { this.nome = dados.nome(); this.cpf =
	 * dados.cpf(); this.telefone = dados.telefone(); this.endereco = new
	 * Endereco(dados.endereco()); this.usuario = usuario; }
	 */
	
	
	public Cliente(@Valid ClienteDTO dados, Usuario usuario) {
		this.nome = dados.nome();
		this.cpf = dados.cpf();
		this.telefone = dados.telefone();
		this.endereco = new Endereco(dados.endereco());
		this.usuario = usuario;
	}

	/*
	 * public Cliente(String nome, String cpf, String telefone) { this.nome = nome;
	 * this.cpf = cpf; this.telefone = telefone; }
	 * 
	 * public Cliente(ClienteDTO dados) { this.id = dados.getId(); this.nome =
	 * dados.getNome(); this.cpf = dados.getCpf(); this.telefone =
	 * dados.getTelefone(); this.endereco = new Endereco(dados.getEndereco()); }
	 */

	public Cliente(String nome, String cpf, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}


	public Cliente(ClienteDTO obj) {
		this.nome = obj.nome();
		this.cpf = obj.cpf();
		this.telefone = obj.telefone();
		this.endereco = new Endereco(obj.endereco());
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", endereco="
				+ endereco + ", usuario=" + usuario + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, endereco, id, nome, pedidos, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(pedidos, other.pedidos) && Objects.equals(telefone, other.telefone);
	}

}
