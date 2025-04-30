package com.xpe.desaffio_final.ecommerce.dto;

import java.util.Optional;

import com.xpe.desaffio_final.ecommerce.modelo.Cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(Long id,

		@NotBlank(message = "{nome.obrigatorio}") String nome,

		@NotBlank(message = "cpf.obrigatorio") String cpf,

		String telefone,

		//@NotNull(message = "{endereco.obrigatorio}") 
		@Valid EnderecoDTO endereco,

		@Valid UsuarioLoginDTO usuario) {

	
	public ClienteDTO(Optional<Cliente> obj) {
		this(obj.get().getId(), obj.get().getNome(), obj.get().getCpf(), obj.get().getTelefone(), new EnderecoDTO(obj.get().getEndereco()), new UsuarioLoginDTO(obj.get().getUsuario()));
	}

	public ClienteDTO(Cliente obj) {
		this(obj.getId(), obj.getNome(), obj.getCpf(), obj.getTelefone(), new EnderecoDTO(obj.getEndereco()), new UsuarioLoginDTO(obj.getUsuario()));

	}

		
}
