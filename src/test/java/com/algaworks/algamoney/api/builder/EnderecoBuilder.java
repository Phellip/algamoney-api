package com.algaworks.algamoney.api.builder;

import com.algaworks.algamoney.api.models.Endereco;

public class EnderecoBuilder {

	private Endereco endereco;
	
	private EnderecoBuilder() {}

	public static EnderecoBuilder umEnderecoValido() {
		EnderecoBuilder builder = new EnderecoBuilder();
		builder.endereco = new Endereco();
		
		builder.endereco.setBairro("Boa Esperança");
		builder.endereco.setCep("78068-765");
		builder.endereco.setCidade("Cuiabá");
		builder.endereco.setEstado("MT");
		builder.endereco.setLogradouro("Rua 08");
		builder.endereco.setNumero("283");
		
		return builder;
	}
	
	public static EnderecoBuilder umEnderecoInvalido() {
		EnderecoBuilder builder = new EnderecoBuilder();
		builder.endereco = new Endereco();
		
		builder.endereco.setCep("78068-765");
		builder.endereco.setEstado("MT");
		builder.endereco.setLogradouro("Rua 08");
		builder.endereco.setNumero("283");
		
		return builder;
	}
	
	public Endereco agora() {
		return this.endereco;
	}
	
}
