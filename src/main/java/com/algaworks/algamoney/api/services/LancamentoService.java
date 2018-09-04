package com.algaworks.algamoney.api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.LancamentoRepository;

@Service
public class LancamentoService {

	private LancamentoRepository lancamentoRepository;
	
	public LancamentoService(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}
	
	public List<Lancamento> consultarLancamentos() {
		List<Lancamento> list = lancamentoRepository.findAll();
		if(Objects.isNull(list) || list.isEmpty()) {
			throw new EmptyResultDataAccessException(Integer.MAX_VALUE);
		}
		
		return list;
	}

	public Lancamento consultarPorCodigo(Long codigo) {
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		if(Objects.isNull(lancamento)) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamento;
	}

	public Lancamento salvar(Lancamento lancamento) {
		lancamento = Objects.requireNonNull(lancamento, "Lancamento é obrigatório.");
		return lancamentoRepository.save(lancamento);
	}
	
}