package com.algaworks.algamoney.api.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.lancamento.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery, JpaSpecificationExecutor<Lancamento> {
	
		@Query("SELECT l " +
		       " FROM Lancamento l " +
		       "   JOIN FETCH l.categoria c " +
		       "   JOIN FETCH l.pessoa p ")
	    @Override
		List<Lancamento> findAll();
}
