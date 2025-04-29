package com.xpe.desaffio_final.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xpe.desaffio_final.ecommerce.modelo.Categoria;

import br.com.alura.unicommerce.vo.RelatorioDeVendasCategoriaVo;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query( "SELECT new br.com.alura.unicommerce.vo.RelatorioDeVendasCategoriaVo(" 
			+ "categoria.nome, " 
			+ "SUM(item.quantidade) as quantidade, " 
			+ "SUM(item.quantidade * (item.precoUnitario - item.desconto)) as montante) " 
			+ "FROM Pedido pedido " 
			+ "JOIN pedido.itemPedidos item " 
			+ "JOIN item.produto produto " 
			+ "JOIN produto.categoria categoria " 
			+ "GROUP BY categoria.nome " 
			+ "ORDER BY montante DESC ")
	Page<RelatorioDeVendasCategoriaVo> getRelatorioVendasPorCategoria(Pageable pageable);
	


}
