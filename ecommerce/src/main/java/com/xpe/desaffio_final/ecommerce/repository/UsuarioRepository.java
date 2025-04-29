package com.xpe.desaffio_final.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.xpe.desaffio_final.ecommerce.modelo.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

}