package com.xpe.desaffio_final.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpe.desaffio_final.ecommerce.dto.UsuarioLoginDTO;
import com.xpe.desaffio_final.ecommerce.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioLoginDTO> findById(@PathVariable @Valid Long id) {
		var dto = service.buscaPorId(id);
		return ResponseEntity.ok().body(new UsuarioLoginDTO(dto));
	}

	/*
	 * @GetMapping(value = "/profile") public ResponseEntity<UsuarioDTO> profile() {
	 * var dto = service.profile(); return ResponseEntity.ok().body(dto); }
	 * 
	 * @PostMapping public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid
	 * UsuarioInsertDTO dto) { UsuarioDTO newDto = service.insert(dto); URI uri =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	 * .buildAndExpand(newDto.getId()).toUri(); return
	 * ResponseEntity.created(uri).body(newDto); }
	 */
	
	
	/*
	 * @PutMapping(value = "/{id}") public ResponseEntity<UsuarioDTO>
	 * update(@PathVariable Long id, @RequestBody @Valid UserUpdateDTO dto) {
	 * UsuarioDTO newDto = service.update(id, dto); return
	 * ResponseEntity.ok().body(newDto); }
	 */

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
