package com.algaworks.algamoney.api.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.algamoney.api.models.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
		@Query("SELECT l " +
		       " FROM Lancamento l " +
		       "   JOIN FETCH l.categoria c " +
		       "   JOIN FETCH l.pessoa p ")
	    @Override
		List<Lancamento> findAll();
}
