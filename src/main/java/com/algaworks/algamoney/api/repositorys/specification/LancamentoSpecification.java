package com.algaworks.algamoney.api.repositorys.specification;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.criterion.MatchMode;
import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.models.Lancamento_;

public class LancamentoSpecification {

	public static Specification<Lancamento> byDescricaoLike(String descricao) {
		if(Objects.isNull(descricao)) {
			return null;
		}
		
		return (root, criteriaQuery, builder) ->
			builder.like(builder.lower(root.get(Lancamento_.descricao)),MatchMode.ANYWHERE.toMatchString(descricao.toLowerCase()));
	}
	
	public static Specification<Lancamento> byDataVencimentoMaiorIgual(LocalDate data) {
		if(Objects.isNull(data)) {
			return null;
		}
		
		return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), data);
	}
	
	public static Specification<Lancamento> byDataVencimentoMenorIgual(LocalDate data) {
		if(Objects.isNull(data)) {
			return null;
		}
		
		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), data);
	}
}
