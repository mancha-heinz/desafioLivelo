package com.gabriel.desafiolivelo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabriel.desafiolivelo.domain.Cliente;
import com.gabriel.desafiolivelo.repositories.CidadeRepository;
import com.gabriel.desafiolivelo.repositories.ClienteRepository;
import com.gabriel.desafiolivelo.services.exceptions.DataIntegrityException;
import com.gabriel.desafiolivelo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cliente findId(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ", tipo: " + Cliente.class.getName(), null));
	}

	public Cliente update(Cliente obj) {
		findId(obj.getId()); // verifica id
		return repo.save(obj);
	}

	public void delete(Integer id) {
		findId(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("nao eh possivel excluir o cliente");
		}
	}
}
