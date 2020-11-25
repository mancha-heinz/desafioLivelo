package com.gabriel.desafiolivelo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriel.desafiolivelo.domain.Cidade;
import com.gabriel.desafiolivelo.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	@Query("SELECT DISTINCT obj FROM Cliente obj INNER JOIN obj.cidade cid WHERE obj.nome LIKE %:nome% AND cid IN :cidade")
	Page<Cliente> search(@Param("nome") String nome, @Param("cidade") List<Cidade> cidade, Pageable pageRequest);
}
