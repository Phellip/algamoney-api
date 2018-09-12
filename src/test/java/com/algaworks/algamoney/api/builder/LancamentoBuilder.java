package com.algaworks.algamoney.api.builder;

import com.algaworks.algamoney.api.models.Categoria;
import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.models.Pessoa;

public class LancamentoBuilder {

	private Lancamento lancamento;
	
	private LancamentoBuilder () {
		//nao sera usado.
	}

	public static LancamentoBuilder umLancamento() {
		LancamentoBuilder builder = new LancamentoBuilder();
		builder.lancamento = new Lancamento();
		return builder;
	}
	
	public static LancamentoBuilder umLancamentoParaSalvar() {
		LancamentoBuilder builder = new LancamentoBuilder();
		builder.lancamento = new Lancamento();
		builder.lancamento.setPessoa(PessoaBuilder.umaPessoaExistenteEAtiva().agora());
		builder.lancamento.setCategoria(CategoriaBuilder.umaCategoria().agora());
		return builder;
	}
	
	public LancamentoBuilder comCodigo() {
		this.lancamento.setCodigo(1L);
		return this;
	}
	
	public LancamentoBuilder comPessoa(Pessoa pessoa) {
		this.lancamento.setPessoa(pessoa);
		return this;
	}
	
	public Lancamento agora() {
		return this.lancamento;
	}

	public LancamentoBuilder comCategoria(Categoria categoria) {
		this.lancamento.setCategoria(categoria);
		return this;
	}

}
