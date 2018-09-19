package com.algaworks.algamoney.api.repositorys.filter;

import static com.algaworks.algamoney.api.repositorys.specification.LancamentoSpecification.byDataVencimentoMaiorIgual;
import static com.algaworks.algamoney.api.repositorys.specification.LancamentoSpecification.byDataVencimentoMenorIgual;
import static com.algaworks.algamoney.api.repositorys.specification.LancamentoSpecification.byDescricaoLike;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.algaworks.algamoney.api.models.Lancamento;

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


	public Specification<Lancamento> buildSpecification() {
		return where(byDescricaoLike(this.getDescricao()))
				.and(byDataVencimentoMaiorIgual(this.getDataVencimentoDe()))
				.and(byDataVencimentoMenorIgual(this.getDataVencimentoAte()));
	}
}
