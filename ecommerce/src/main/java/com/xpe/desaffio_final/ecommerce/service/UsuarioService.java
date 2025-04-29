package com.xpe.desaffio_final.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xpe.desaffio_final.ecommerce.dto.UsuarioLoginDTO;
import com.xpe.desaffio_final.ecommerce.modelo.Usuario;
import com.xpe.desaffio_final.ecommerce.repository.UsuarioRepository;
import com.xpe.desaffio_final.ecommerce.service.exceptions.DatabaseException;
import com.xpe.desaffio_final.ecommerce.service.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AuthService authService;


	@Autowired
	PasswordEncoder passwordEncoder;

	public <Optinal> Usuario buscaPorId(Long usuarioId) {
		Optional<Usuario> obj = repository.findById(usuarioId);
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return entity;
	}

	/*
	 * @Transactional public UsuarioDTO insert(UsuarioInsertDTO dto) { Usuario
	 * entity = new Usuario(); copyDtoToEntity(dto, entity);
	 * entity.setSenha(passwordEncoder.encode(dto.getSenha())); entity =
	 * repository.save(entity); return new UsuarioDTO(entity); }
	 */

	/*
	 * @Transactional public UsuarioDTO update(Long id, UserUpdateDTO dto) { try {
	 * Usuario entity = repository.getReferenceById(id); copyDtoToEntity(dto,
	 * entity); entity = repository.save(entity); return new UsuarioDTO(entity); }
	 * catch (EntityNotFoundException e) { throw new
	 * ResourceNotFoundException("Id not found " + id); } }
	 */

	public UsuarioLoginDTO profile() {
		Usuario usuario = (Usuario) authService.authenticated();
		return new UsuarioLoginDTO(usuario);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	/*
	 * private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
	 * 
	 * entity.setLogin(dto.getLogin());
	 * 
	 * entity.getRoles().clear(); for (RoleDTO roleDto : dto.getRoles()) { Role role
	 * = roleRepository.getReferenceById(roleDto.getId());
	 * entity.getRoles().add(role); } }
	 */
}
