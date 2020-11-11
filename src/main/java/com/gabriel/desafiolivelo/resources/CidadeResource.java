package com.gabriel.desafiolivelo.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public String listart() {
		return "rest esta funcionando!";
	}
}
