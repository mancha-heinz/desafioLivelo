package com.gabriel.desafiolivelo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.domain.Cliente;
import com.gabriel.desafiolivelo.repositories.CidadeRepository;
import com.gabriel.desafiolivelo.repositories.ClienteRepository;

@SpringBootApplication
public class DesafioliveloApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioliveloApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cidade cid1 = new Cidade(null, "passo fundo", "rs");
		Cidade cid2 = new Cidade(null, "marau", "rs");

		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));

		Cliente c1 = new Cliente();
		c1.setNome("gabriel");
		c1.setSexo("masc");
		c1.setDataNasc("11-11-2020");
		c1.setIdade(20);
		c1.setCidade(cid1);
		Cliente c2 = new Cliente();
		c2.setNome("ana");
		c2.setSexo("fem");
		c2.setDataNasc("01-01-2020");
		c2.setIdade(15);
		c2.setCidade(cid2);
		Cliente c3 = new Cliente();
		c3.setNome("paulo");
		c3.setSexo("masc");
		c3.setDataNasc("03-03-2020");
		c3.setIdade(31);
		c3.setCidade(cid2);

		cid1.getClientes().addAll(Arrays.asList(c1));

		clienteRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

}
