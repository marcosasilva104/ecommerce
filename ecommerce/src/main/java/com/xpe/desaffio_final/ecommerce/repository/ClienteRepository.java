package com.xpe.desaffio_final.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpe.desaffio_final.ecommerce.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
