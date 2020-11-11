package com.gabriel.desafiolivelo.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafiolivelo.domain.Cliente;
import com.gabriel.desafiolivelo.repositories.ClienteRepository;
import com.gabriel.desafiolivelo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscaId(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ", tipo:" + Cliente.class.getName(), null));
	}
}
