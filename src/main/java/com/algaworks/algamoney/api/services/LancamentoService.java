package com.algaworks.algamoney.api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.LancamentoRepository;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;

@Service
public class LancamentoService {

	private LancamentoRepository lancamentoRepository;
	private PessoaService pessoaService;
	
	public LancamentoService(LancamentoRepository lancamentoRepository, PessoaService pessoaService) {
		this.lancamentoRepository = lancamentoRepository;
		this.pessoaService = pessoaService;
	}
	
	public List<Lancamento> consultarLancamentos(LancamentoFilter filter) {
		List<Lancamento> list = lancamentoRepository.filtrar(filter);
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
		Objects.requireNonNull(lancamento, "Lancamento é obrigatório.");
		Objects.requireNonNull(lancamento.getPessoa(), "Pessoa é obrigatório.");
		Objects.requireNonNull(lancamento.getCategoria(), "Categoria é obrigatório.");
		
		pessoaService.verificarPessoaExistenteEAtivo(lancamento.getPessoa().getCodigo());
		
		return lancamentoRepository.save(lancamento);
	}

	public void deletarPorCodigo(Long codigo) {
		Objects.requireNonNull(codigo, "Codigo do Lancamento é obrigatório.");
		lancamentoRepository.delete(codigo);
	}
}
