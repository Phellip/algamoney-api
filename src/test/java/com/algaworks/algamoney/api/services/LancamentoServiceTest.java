package com.algaworks.algamoney.api.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.algaworks.algamoney.api.builder.LancamentoBuilder;
import com.algaworks.algamoney.api.builder.PessoaBuilder;
import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.LancamentoRepository;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;

public class LancamentoServiceTest {

	LancamentoService service;
	
	PessoaService pessoaService;
	
	LancamentoRepository lancamentoRepository;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void init() {
		//configuracao do meu contexto de teste.
		pessoaService = mock(PessoaService.class);
		lancamentoRepository = mock(LancamentoRepository.class);		
		
		service = new LancamentoService(lancamentoRepository, pessoaService);
	}
	
	@Test
	public void deve_retornar_todos_lancamentos() {
		//cenario
		LancamentoFilter filter = new LancamentoFilter();
		List<Lancamento> todosLancamentos = Arrays.asList(new Lancamento(),new Lancamento());
		when(lancamentoRepository.filtrar(filter)).thenReturn(todosLancamentos);
		
		//execucao
		List<Lancamento> lancamentos = service.consultarLancamentos(filter);
		
		//validacao
		assertThat(lancamentos, notNullValue());
		assertThat(lancamentos.isEmpty(), is(false));
		assertThat(lancamentos.size(), is(2));
	}
	
	@Test
	public void nao_deve_retornar_lancamentos_inexistentes() {
		//cenario
		LancamentoFilter filter = new LancamentoFilter();
		List<Lancamento> todosLancamentos = new ArrayList<>();
		when(lancamentoRepository.filtrar(filter)).thenReturn(todosLancamentos);
	
		//execucao
		exception.expect(EmptyResultDataAccessException.class);
		service.consultarLancamentos(filter);
	}
	
	@Test
	public void deve_retornar_um_lancamento() {
		//cenario
		when(lancamentoRepository.findOne(1L)).thenReturn(LancamentoBuilder.umLancamento().agora());
		
		//execucao
		Lancamento lancamento = service.consultarPorCodigo(1L);
		
		//validacao
		assertThat(lancamento, notNullValue());
	}
	
	@Test
	public void nao_deve_retornar_lancamento_para_codigo_nulo() {
		//cenario
		Long id = null;
		when(lancamentoRepository.findOne(id)).thenThrow(new IllegalArgumentException());
		
		//execucao
		exception.expect(IllegalArgumentException.class);
		service.consultarPorCodigo(null);
	}
	
	@Test
	public void nao_deve_retornar_lancamento_para_codigo_inexistente() {
		//cenario
		Long id = 1L;
		when(lancamentoRepository.findOne(id)).thenReturn(null);
		
		//execucao
		exception.expect(EmptyResultDataAccessException.class);
		service.consultarPorCodigo(1L);
	}

	@Test
	public void deve_salvar_lancamento() {
		//cenario
		Lancamento lancamento = LancamentoBuilder.umLancamentoParaSalvar().agora();
		when(lancamentoRepository.save(lancamento)).thenReturn(lancamento);
		
		//execucao
		Lancamento lancamentoSalvo = service.salvar(lancamento);
		
		assertThat(lancamentoSalvo, notNullValue());
	}

	@Test
	public void nao_deve_salvar_lancamento_nulo() {
		//cenario
		Lancamento lancamento = null;
		
		//validacao
		exception.expect(NullPointerException.class);
		exception.expectMessage("Lancamento é obrigatório.");
		
		//execucao
		service.salvar(lancamento);
	}
	
	@Test
	public void nao_deve_salvar_lancamento_com_pessoa_nula() {
		//cenario
		Lancamento lancamento = LancamentoBuilder.umLancamento().comPessoa(null).agora();
	
		//validacao
		exception.expect(NullPointerException.class);
		exception.expectMessage("Pessoa é obrigatório.");

		//execucao
		service.salvar(lancamento);
	}

	@Test
	public void nao_deve_salvar_lancamento_com_categoria_nulo() {
		//cenario
		Lancamento lancamento = LancamentoBuilder.umLancamento().comPessoa(PessoaBuilder.umaPessoa().agora())
				.comCategoria(null).agora();
		
		//validacao
		exception.expect(NullPointerException.class);
		exception.expectMessage("Categoria é obrigatório.");
	
		//execucao
		service.salvar(lancamento);
	}
	
}
