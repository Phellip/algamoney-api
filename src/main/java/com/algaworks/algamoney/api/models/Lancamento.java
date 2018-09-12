package com.algaworks.algamoney.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lancamento")
@EqualsAndHashCode(of = {"codigo"})
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private Long codigo;
	
	@NotBlank
	@Length(max=60)
	@Setter @Getter
	private String descricao;
	
	@NotNull
	@Column(name = "data_vencimento")
	@Setter @Getter
	private LocalDate dataVencimento;
	
	@Column(name = "data_pagamento")
	@Setter @Getter
	private LocalDate dataPagamento;
	
	@NotNull
	@Setter @Getter
	private BigDecimal valor;
	
	@Setter @Getter
	private String observacao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	@Setter @Getter
	private TipoLancamento tipoLancamento;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_categoria")
	@Setter @Getter
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_pessoa")
	@Setter @Getter
	private Pessoa pessoa;
}
