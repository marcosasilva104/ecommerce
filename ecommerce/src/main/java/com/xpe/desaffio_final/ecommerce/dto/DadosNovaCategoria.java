package com.xpe.desaffio_final.ecommerce.dto;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;

import jakarta.validation.constraints.NotEmpty;

public record DadosNovaCategoria(@NotEmpty 
		     @NotNull 
		     @Length(min = 2) 
             String nome) {
	
	public Categoria toEntity() {
		return new Categoria(this.nome);
	}

}