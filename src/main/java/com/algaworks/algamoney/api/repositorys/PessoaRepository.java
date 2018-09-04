package com.algaworks.algamoney.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algamoney.api.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Transactional
	@Modifying 
	@Query(value="UPDATE Pessoa SET ativo=:ativo WHERE codigo = :codigo")
	public void atualizarAtivo( @Param("ativo") Boolean ativo, @Param("codigo") Long codigo);
}
