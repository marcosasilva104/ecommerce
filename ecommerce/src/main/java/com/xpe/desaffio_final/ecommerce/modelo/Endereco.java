package com.xpe.desaffio_final.ecommerce.modelo;

import com.xpe.desaffio_final.ecommerce.dto.EnderecoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 */
	
	@Column(name = "rua", nullable = false, length = 120)
	private String rua;
	
	@Column(name = "numero", length = 10, nullable = false)
	private String numero;
	
	@Column(name = "complemento", nullable = false, length = 150)
	private String complemento;
	
	@Column(name = "bairro", length = 150, nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false, length = 150)
	private String cidade;
	
	@Column(name = "estado", nullable = false, length = 2)
	private String estado;
	
	public Endereco() {}

	public Endereco(String rua, String numero, String complemento, String bairro, String cidade,
			String estado) {
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Endereco(EnderecoDTO dados) {
		 this.rua = dados.rua();
	     this.bairro = dados.bairro();
	     this.estado = dados.estado();
	     this.cidade = dados.cidade();
	     this.numero = dados.numero();
	     this.complemento = dados.complemento();
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Endereco [rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", estado=" + estado + "]";
	}

}
