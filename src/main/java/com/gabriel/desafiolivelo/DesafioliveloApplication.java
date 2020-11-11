package com.gabriel.desafiolivelo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.repositories.CidadeRepository;

@SpringBootApplication
public class DesafioliveloApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioliveloApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cidade cid1 = new Cidade(null, "passo fundo", "rs");
		Cidade cid2 = new Cidade(null, "marau", "rs");

		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));

	}

}
