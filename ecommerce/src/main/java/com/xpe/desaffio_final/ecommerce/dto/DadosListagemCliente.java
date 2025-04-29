package com.xpe.desaffio_final.ecommerce.dto;

import com.xpe.desaffio_final.ecommerce.modelo.Cliente;

public record DadosListagemCliente(String nome, String cpf, String telefone) {

	public DadosListagemCliente(Cliente cliente) {
        this(cliente.getNome(), cliente.getCpf(), cliente.getTelefone());
    }
}
