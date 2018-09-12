package com.algaworks.algamoney.api.repositorys.lancamento;

import java.util.List;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	List<Lancamento> filtrar(LancamentoFilter filter);
}
