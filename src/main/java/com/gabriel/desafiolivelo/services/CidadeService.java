package com.gabriel.desafiolivelo.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.repositories.CidadeRepository;
import com.gabriel.desafiolivelo.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ", tipo:" + Cidade.class.getName(), null));
	}
}
