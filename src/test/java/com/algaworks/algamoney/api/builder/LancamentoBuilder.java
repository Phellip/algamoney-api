package com.algaworks.algamoney.api.builder;

import com.algaworks.algamoney.api.models.Lancamento;

public class LancamentoBuilder {

	private Lancamento lancamento;
	
	private LancamentoBuilder () {
		//nao sera usado.
	}

	public static LancamentoBuilder umLancamento() {
		LancamentoBuilder builder = new LancamentoBuilder();
		builder.lancamento = new Lancamento();
		builder.lancamento.setPessoa(PessoaBuilder.umaPessoa().agora()); 
		return builder;
	}
	
	public Lancamento agora() {
		return this.lancamento;
	}
}
