package com.algaworks.algamoney.api.repositorys.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	Page<Lancamento> filtrarPaginado(LancamentoFilter filter, Pageable pageable);
}
