package com.xpe.desaffio_final.ecommerce.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.xpe.desaffio_final.ecommerce.dto.ClienteDTO;
import com.xpe.desaffio_final.ecommerce.dto.DadosListagemCliente;
import com.xpe.desaffio_final.ecommerce.dto.UsuarioLoginDTO;
import com.xpe.desaffio_final.ecommerce.modelo.Cliente;
import com.xpe.desaffio_final.ecommerce.modelo.Usuario;
import com.xpe.desaffio_final.ecommerce.service.ClienteService;
import com.xpe.desaffio_final.ecommerce.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {

	@Autowired
	ClienteService service;

	 @Autowired
	 UsuarioService serviceUsuario;

	@PostMapping
	@Transactional
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid ClienteDTO dados, BindingResult result,
			UriComponentsBuilder uriBuilder) {
		if (result.hasErrors())
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		UsuarioLoginDTO usuarioId = dados.usuario();

		Long idUsuario = usuarioId.id();

		var dadosIdUsuario = serviceUsuario.buscaPorId(idUsuario);

		Optional<Usuario> obj = Optional.ofNullable(serviceUsuario.buscaPorId(idUsuario));

		if (!obj.isEmpty()) {

			Cliente cliente = new Cliente(dados, dadosIdUsuario);

			service.inserirCliente(cliente);

			URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

			return ResponseEntity.created(uri).body(new DadosListagemCliente(cliente));
		}
		return null;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscaClientePorId(@PathVariable("id") Long clienteId) {

		if (clienteId == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		Optional<Cliente> cliente = service.buscaPorId(clienteId);

		if (cliente.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

		return ResponseEntity.status(HttpStatus.OK).body(new ClienteDTO(cliente));
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<Object> update(@PathVariable("id") Long clienteId, @RequestBody ClienteDTO dados) {
		ClienteDTO cliente = service.update(clienteId, dados);
		return ResponseEntity.ok().body(cliente);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable("id") Long clienteId) {
		service.delete(clienteId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemCliente>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		Page<DadosListagemCliente> list = service.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
	}

}
