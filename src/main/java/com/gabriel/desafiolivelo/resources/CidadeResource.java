package com.gabriel.desafiolivelo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.desafiolivelo.domain.Cidade;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Cidade> listart() {
		Cidade cid1 = new Cidade(1, "passo fundo", "rs");
		Cidade cid2 = new Cidade(2, "marau", "rs");
		
		List<Cidade> lista=new ArrayList<>();
		lista.add(cid1);
		lista.add(cid2);
		
		return lista;
	}
}
