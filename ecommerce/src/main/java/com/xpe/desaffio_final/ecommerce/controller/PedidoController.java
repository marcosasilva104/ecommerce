package com.xpe.desaffio_final.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.xpe.desaffio_final.ecommerce.dto.DadosDePedido;
import com.xpe.desaffio_final.ecommerce.dto.PedidoDTO;
import com.xpe.desaffio_final.ecommerce.modelo.Pedido;
import com.xpe.desaffio_final.ecommerce.service.ClienteService;
import com.xpe.desaffio_final.ecommerce.service.PedidoService;
import com.xpe.desaffio_final.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/pedidos")
@SecurityRequirement(name = "bearer-key")
public class PedidoController {

	@Autowired
	PedidoService service;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ProdutoService produtoService;

	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastraPedido(@RequestBody @Valid DadosDePedido dadosDePedido,
			UriComponentsBuilder uriBuilder, BindingResult result) {
		
		try {
			if (result.hasErrors())
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			Pedido novoPedido = dadosDePedido.converter(clienteService, produtoService);
			service.insert(novoPedido);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dadosDePedido)
					.toUri();
			return ResponseEntity.created(uri).body(dadosDePedido);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocorreu uma falha no cadastro do pedido");
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Pedido> update(@PathVariable("id") Long pedidoId, @RequestBody Pedido pedido){
		pedido = service.update(pedidoId, pedido);
		return ResponseEntity.ok().body(pedido);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Pedido> delete(@PathVariable("id") Long pedidoId){
		service.delete(pedidoId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscaPedidoPorId(@PathVariable("id") Long pedidoId) {

		if (pedidoId == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		Optional<Pedido> pedido = service.buscaPorId(pedidoId);

		if (pedido.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		return ResponseEntity.status(HttpStatus.OK).body(new PedidoDTO(pedido.get()));
	}

	

}
