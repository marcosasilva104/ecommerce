package com.xpe.desaffio_final.ecommerce.dto;

import com.xpe.desaffio_final.ecommerce.modelo.Usuario;

public record UsuarioLoginDTO (Long id, String login) {

	public UsuarioLoginDTO(Usuario dados) {
		this(dados.getId() ,dados.getLogin());
	}
	
}
