package com.gabriel.desafiolivelo.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.repositories.CidadeRepository;
import com.gabriel.desafiolivelo.services.exceptions.DataIntegrityException;
import com.gabriel.desafiolivelo.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade findId(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"objeto nao encontrado! id: " + id + ", tipo:" + Cidade.class.getName(), null));
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null); //caso o id for diferente de null o save considera como update
		return repo.save(obj);
	}
	
	public Cidade update(Cidade obj) {
		findId(obj.getId()); //verifica id
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		findId(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("nao eh possivel excluir uma cidaede que possuia pessoas relacionadas");
		}
	}
	
	public List<Cidade> findAll(){
		return repo.findAll();
	}
}
