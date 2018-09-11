package com.algaworks.algamoney.api.builder;

import com.algaworks.algamoney.api.models.Pessoa;

public class PessoaBuilder {

	private Pessoa pessoa;
	
	private PessoaBuilder() {
		//nao sera instanciado.
	}

	public static PessoaBuilder umaPessoa() {
		PessoaBuilder builder  = new PessoaBuilder();
		builder.pessoa = new Pessoa();
		builder.pessoa.setAtivo(true);
		
		return builder;
	}

	public Pessoa agora() {
		return this.pessoa;
	}
}
