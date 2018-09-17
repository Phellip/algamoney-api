package com.algaworks.algamoney.api.services.exception;

import lombok.Getter;

public class PessoaInexistenteOuInativaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String messageKey;
	
	public PessoaInexistenteOuInativaException(String key) {
		this.messageKey =  key;
	}
}
