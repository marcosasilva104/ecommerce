package com.xpe.desaffio_final.ecommerce.dto;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EnderecoDTO(
		@NotBlank
		String rua,
		
		@NotNull
		@Positive
		String numero, 
		
		String complemento, 
		
		@NotBlank
		String bairro, 
			
		@NotBlank
		String cidade,
		
		@NotBlank
		String estado) {

	public EnderecoDTO(Endereco dados) {
		this(dados.getRua(), dados.getNumero(), dados.getComplemento(), dados.getBairro(), dados.getCidade(), dados.getEstado());
	}
;
}

