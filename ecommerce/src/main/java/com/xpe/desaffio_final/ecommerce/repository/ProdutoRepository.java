package com.xpe.desaffio_final.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpe.desaffio_final.ecommerce.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
