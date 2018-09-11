package com.algaworks.algamoney.api.services.exception;

public class PessoaInexistenteOuInativaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PessoaInexistenteOuInativaException(String string) {
		super(string);
	}
}
