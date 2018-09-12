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
	
	public static PessoaBuilder umaPessoaExistenteEAtiva() {
		PessoaBuilder builder  = new PessoaBuilder();
		builder.pessoa = new Pessoa();
		builder.pessoa.setAtivo(true);
		builder.pessoa.setCodigo(1L);
		return builder;
	}

	public PessoaBuilder comCodigo(Long codigo) {
		this.pessoa.setCodigo(codigo);
		return this;
	}
	
	public Pessoa agora() {
		return this.pessoa;
	}
}
