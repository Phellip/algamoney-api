package com.algaworks.algamoney.api.handlers;

import java.net.URI;

import lombok.Getter;

public enum AlgaMoneyProblemType {
	
	DATA_INTEGRITY_VIOLATION("Data Integrity Violation",URI.create("http://localhost:8080/problem/data-integrty-violation")),
	PESSOA_INEXISTENTE_INATIVA("Pessoa Inexistente/Inativa",URI.create("http://localhost:8080/problem/pessoa-inexistente-inativa")),
	EMPTY_RESULT_DATA("Recurso não contém dados",URI.create("http://localhost:8080/problem/empty-result-data"));

	@Getter
	private String title;
	
	@Getter
	private URI uri;
	
	private AlgaMoneyProblemType(String title, URI uri) {
		this.title = title;
		this.uri = uri;
	}
}
