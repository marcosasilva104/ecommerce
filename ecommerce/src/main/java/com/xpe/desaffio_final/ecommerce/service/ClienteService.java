package com.xpe.desaffio_final.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpe.desaffio_final.ecommerce.dto.ClienteDTO;
import com.xpe.desaffio_final.ecommerce.dto.DadosListagemCliente;
import com.xpe.desaffio_final.ecommerce.modelo.Cliente;
import com.xpe.desaffio_final.ecommerce.modelo.Pedido;
import com.xpe.desaffio_final.ecommerce.repository.ClienteRepository;
import com.xpe.desaffio_final.ecommerce.service.exceptions.DatabaseException;
import com.xpe.desaffio_final.ecommerce.service.exceptions.ResourceNotFoundException;
import com.xpe.desaffio_final.ecommerce.vo.RelatorioDeVendasClienteVo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	public List<RelatorioDeVendasClienteVo> getRelatorioClientesFieis(List<Pedido> pedidos) {

		List<RelatorioDeVendasClienteVo> relatorioClientesFieis = new ArrayList<>();

		pedidos.forEach(pedido -> {
			relatorioClientesFieis
					.add(new RelatorioDeVendasClienteVo(pedido.getCliente().getNome(), 1L, pedido.getValorTotal()));
		});

		return relatorioClientesFieis;
	}

	@Autowired
	private ClienteRepository repository;

	
	public Optional<Cliente> buscaPorId(Long clienteId) {
		Optional<Cliente> resultCliente = repository.findById(clienteId); // return Optional
		Cliente cliente = resultCliente.get(); // return Cliente
		return Optional.ofNullable(cliente);

	}

	@Transactional(readOnly = true)
	public Page<DadosListagemCliente> findAllPaged(PageRequest pageRequest) {
		Page<Cliente> list = repository.findAll(pageRequest);
		return list.map(x -> new DadosListagemCliente(x));
	}

	
	 
	  public void inserirCliente(Cliente cliente) {

			//System.out.println(cliente);

			if (cliente == null)
				throw new IllegalArgumentException("Falha ao cadastrar cadastrar o cliente.");
			repository.save(cliente);
		}
	

	public ClienteDTO update(Long clienteId, ClienteDTO cliente) {
		try {
			Cliente entity = repository.getReferenceById(clienteId);
			entity = repository.save(new Cliente(cliente));
			return new ClienteDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + clienteId);
		}
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

}
