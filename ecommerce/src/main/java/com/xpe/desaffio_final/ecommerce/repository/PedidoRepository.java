package com.xpe.desaffio_final.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpe.desaffio_final.ecommerce.modelo.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	
}
