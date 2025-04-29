package com.xpe.desaffio_final.ecommerce.dto;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;

public record DadosListagemCategoria(Long id, String nome, boolean status) {

	public DadosListagemCategoria(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.isStatus());
    }
}
