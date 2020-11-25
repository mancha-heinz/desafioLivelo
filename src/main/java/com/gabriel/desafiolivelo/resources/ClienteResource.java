package com.gabriel.desafiolivelo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.domain.Cliente;
import com.gabriel.desafiolivelo.dto.CidadeDTO;
import com.gabriel.desafiolivelo.dto.ClienteDTO;
import com.gabriel.desafiolivelo.dto.ClienteNewDTO;
import com.gabriel.desafiolivelo.resources.utils.URL;
import com.gabriel.desafiolivelo.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente obj = service.findId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ClienteNewDTO objDto) { //json é convertido em obj java
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri(); //ao fazer um post retorna a uri do novo registro inserido
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Cliente obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value ="nome", defaultValue = "" ) String nome,
			@RequestParam(value ="cidade", defaultValue = "" ) String cidades,
			@RequestParam(value ="page", defaultValue = "0" ) Integer page,
			@RequestParam(value ="linesPage", defaultValue = "24" ) Integer linesPage,
			@RequestParam(value ="orderBy", defaultValue = "nome" ) String orderBy,
			@RequestParam(value ="direction", defaultValue = "ASC" ) String direction){
		String nomeDecode=URL.decodeParam(nome);
		List<Integer> ids=URL.decodeIntList(cidades);
		Page<Cliente> list = service.search(nomeDecode, ids, page, linesPage, orderBy, direction);
		Page<ClienteDTO> listaDto=list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listaDto);
	}
}
