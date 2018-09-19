package com.algaworks.algamoney.api.services;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.algaworks.algamoney.api.builder.PessoaBuilder;
import com.algaworks.algamoney.api.models.Pessoa;
import com.algaworks.algamoney.api.repositorys.PessoaRepository;

public class PessoaServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	PessoaService service;
	
	PessoaRepository repository;
	
	@Before
	public void before() {
		repository = Mockito.mock(PessoaRepository.class);
		service = new PessoaService(repository);
	}
	
	@Test
	public void deve_salvar() {
		//cenario
		Optional<Pessoa> pessoaOptional =  Optional.of(PessoaBuilder.umaPessoa().agora());
		
		//execucao
		service.salvar(pessoaOptional);
	
		//verificacao
		Mockito.verify(repository).save(pessoaOptional.get());
	}

	@Test
	public void nao_deve_salvar_pessoa_nula() {
		//cenario
		Optional<Pessoa> pessoaOptional = Optional.ofNullable(null);
		
		exception.expect(IllegalArgumentException.class);
		
		//execucao
		service.salvar(pessoaOptional);
	
		//validacao
		Mockito.verify(repository).save(pessoaOptional.get());
	}
	
	@Test
	public void nao_deve_salvar_com_dados_invalidos() {
		//cenario
		Optional<Pessoa> pessoaOptional = Optional.ofNullable( PessoaBuilder.umaPessoaInvalida().agora());
		
		exception.expect(ConstraintViolationException.class);
		
		//execucao
		service.salvar(pessoaOptional);
	
		//validacao
		Mockito.verify(repository).save(pessoaOptional.get());
	}
}
