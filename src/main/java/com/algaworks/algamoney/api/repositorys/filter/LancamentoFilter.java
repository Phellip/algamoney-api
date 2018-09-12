package com.algaworks.algamoney.api.repositorys.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

public class LancamentoFilter {
	
	@Setter @Getter
	private String descricao;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Setter @Getter
	private LocalDate dataVencimentoDe;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Setter @Getter
	private LocalDate dataVencimentoAte;
}
