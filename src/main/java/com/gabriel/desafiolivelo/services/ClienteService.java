package com.gabriel.desafiolivelo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.domain.Cliente;
import com.gabriel.desafiolivelo.dto.ClienteNewDTO;
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

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
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

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getSexo(), objDto.getDataNasc(),
				objDto.getIdade());
		Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
		// cidade.getClientes().add(cliente); //remover
		return cliente;
	}

	// metodo de consultar nome
	public Page<Cliente> search(String nome, List<Integer> ids, Integer page, Integer linesPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		List<Cidade> cidades = cidadeRepository.findAll();
		return repo.search(nome, cidades, pageRequest);
	}
}
