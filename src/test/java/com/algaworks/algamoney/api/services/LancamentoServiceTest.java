package com.algaworks.algamoney.api.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.algaworks.algamoney.api.builder.LancamentoBuilder;
import com.algaworks.algamoney.api.builder.PessoaBuilder;
import com.algaworks.algamoney.api.models.Categoria;
import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.LancamentoRepository;

public class LancamentoServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	LancamentoService service;
	
	PessoaService pessoaService;
	
	LancamentoRepository lancamentoRepository;
	
	
	@Before
	public void init() {
		//configuracao do meu contexto de teste.
		pessoaService = mock(PessoaService.class);
		lancamentoRepository = mock(LancamentoRepository.class);		
		
		service = new LancamentoService(lancamentoRepository, pessoaService);
	}
	
	@Test
	public void deve_retornar_um_lancamento() {
		//cenario
		when(lancamentoRepository.findOne(1L)).thenReturn(LancamentoBuilder.umLancamento().agora());
		Optional<Long> codigoOptional = Optional.of(1L);
		
		//execucao
		Lancamento lancamento = service.consultarPorCodigo(codigoOptional);
		
		//validacao
		assertThat(lancamento, notNullValue());
	}
	
	@Test
	public void nao_deve_retornar_lancamento_para_codigo_nulo() {
		//cenario
		Optional<Long> codigoOptional = Optional.ofNullable(null);
		
		//validacao
		exception.expect(IllegalArgumentException.class);
		
		//execucao
		service.consultarPorCodigo(codigoOptional);
	}
	
	@Test
	public void nao_deve_retornar_lancamento_para_codigo_inexistente() {
		//cenario
		Optional<Long> codigoOptional = Optional.of(1L);
		when(lancamentoRepository.findOne(codigoOptional.get())).thenReturn(null);
		
		//validacao
		exception.expect(EmptyResultDataAccessException.class);
		
		//execucao
		service.consultarPorCodigo(codigoOptional);
	}

	@Test
	public void deve_salvar_lancamento() {
		//cenario
		Optional<Lancamento> lancamentoOptional = Optional.of(LancamentoBuilder.umLancamentoParaSalvar().agora());
		when(lancamentoRepository.save(lancamentoOptional.get())).thenReturn(lancamentoOptional.get());
		
		//execucao
		Lancamento lancamentoSalvo = service.salvar(lancamentoOptional);
		
		error.checkThat(lancamentoSalvo, notNullValue());
	}

	@Test
	public void nao_deve_salvar_lancamento_nulo() {
		//cenario
		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(null);
		
		//validacao
		exception.expect(IllegalArgumentException.class);
		
		//execucao
		service.salvar(lancamentoOptional);
	}
	
	@Test
	public void nao_deve_salvar_lancamento_com_pessoa_nula() {
		//cenario
		Optional<Lancamento> lancamentoOptional = Optional.ofNullable(LancamentoBuilder
													.umLancamento()
													.comCategoria(new Categoria())
													.comPessoa(null)
													.agora());
		
		//execucao
		try {
			service.salvar(lancamentoOptional);
			Assert.fail("Deveria falhar para pessoa nula em lancamento.");
		} catch(ConstraintViolationException ex) {
			//validacao
			Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
 			error.checkThat(violations.size(),is(1));
		}
	}

	@Test
	public void nao_deve_salvar_lancamento_com_categoria_nulo() {
		//cenario
		Optional<Lancamento> lancamentoOptional = Optional.of(LancamentoBuilder.umLancamento().comPessoa(PessoaBuilder.umaPessoa().agora())
				.comCategoria(null).agora());
		
		try {
			//execucao
			service.salvar(lancamentoOptional);
			Assert.fail("Deveria falhar para categoria nula em lancamento.");
		} catch(ConstraintViolationException ex) {
			//validacao
			Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
 			error.checkThat(violations.size(),is(1));
		}
	}
	
}
