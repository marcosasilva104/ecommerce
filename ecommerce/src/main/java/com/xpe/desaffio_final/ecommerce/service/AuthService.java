package com.xpe.desaffio_final.ecommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xpe.desaffio_final.ecommerce.repository.UsuarioRepository;
import com.xpe.desaffio_final.ecommerce.service.exceptions.UnauthorizedException;

import jakarta.transaction.Transactional;

@Service
public class AuthService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional
	public UserDetails authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return usuarioRepositorio.findByLogin(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails usuario = repository.findByLogin(username);

		if (usuario == null) {
			logger.error("Usuário não encontado: " + username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
		logger.info("Usuário encontrado: " + username);
		return usuario;
	}

	/*
	 * public void validateSelfOrAdmin(Long userId) { UserDetails usuario =
	 * authenticated(); if (!((Usuario) usuario).getId().equals(userId) &&
	 * !((Usuario) usuario).hasRole("ROLE_ADMIN")) { throw new
	 * ForbiddenException("Access denied"); } }
	 */

}
