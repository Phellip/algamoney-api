package com.algaworks.algamoney.api.services;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.LancamentoRepository;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;
import com.algaworks.algamoney.api.util.BeanValidationUtil;

@Service
public class LancamentoService {

	private LancamentoRepository lancamentoRepository;
	private PessoaService pessoaService;
	
	public LancamentoService(LancamentoRepository lancamentoRepository, PessoaService pessoaService) {
		this.lancamentoRepository = lancamentoRepository;
		this.pessoaService = pessoaService;
	}
	
	public Page<Lancamento> consultarLancamentos(LancamentoFilter filter,Pageable pageable) {
		Page<Lancamento> pageLancamento = lancamentoRepository.findAll(filter.buildSpecification(),pageable);
		
		if(pageLancamento.getTotalElements() < 1) {
			throw new EmptyResultDataAccessException(pageLancamento.getSize());
		}
		
		return pageLancamento;
	}
	
	public Lancamento consultarPorCodigo(Optional<Long> optionalCodigo) {
		Long codigo = optionalCodigo.orElseThrow(IllegalArgumentException::new);
		
		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(lancamentoRepository.findOne(codigo));
		
		if(!lancamentoOptional.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamentoOptional.get();
	}

	public Lancamento salvar(Optional<Lancamento> lancamentoOptional) {
		Lancamento lancamento = lancamentoOptional.orElseThrow(IllegalArgumentException::new);
		BeanValidationUtil.validateOrThrowConstraintException(lancamento);
		pessoaService.verificarPessoaExistenteEAtivo(lancamento.getPessoa().getCodigo());
		
		return lancamentoRepository.save(lancamento);
	}

	public void deletarPorCodigo(Optional<Long> codigoOptional) {
		Long codigo = codigoOptional.orElseThrow(IllegalArgumentException::new);
		lancamentoRepository.delete(codigo);
	}
}
