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
		builder.pessoa.setNome("Pessoa Mockito");		
		builder.pessoa.setAtivo(true);
		builder.pessoa.setEndereco(EnderecoBuilder.umEnderecoValido().agora());
		
		return builder;
	}
	
	public static PessoaBuilder umaPessoaInvalida() {
		PessoaBuilder builder  = new PessoaBuilder();
		builder.pessoa = new Pessoa();
		builder.pessoa.setAtivo(false);
		builder.pessoa.setEndereco(EnderecoBuilder.umEnderecoInvalido().agora());
		
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
