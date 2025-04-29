package com.xpe.desaffio_final.ecommerce.dto;

import org.antlr.v4.runtime.misc.NotNull;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;

public record DadosNovaCategoria(@NotEmpty 
		     @NotNull 
		     @Length(min = 2) 
             String nome) {
	
	public Categoria toEntity() {
		return new Categoria(this.nome);
	}

}