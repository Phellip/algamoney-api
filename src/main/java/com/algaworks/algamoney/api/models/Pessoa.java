package com.algaworks.algamoney.api.models;

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Table(name = "pessoa")
@EqualsAndHashCode(of= {"codigo"})
public class Pessoa {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private Long codigo;
	
	@NotBlank
	@Size(min=10,max=60)
	@Basic(optional=false)
	@Setter @Getter
	private String nome;
	
	@Basic(optional=true)
	@Setter @Getter
	private Boolean ativo;
	
	@Valid
	@Embedded
	@Setter @Getter
	private Endereco endereco;

	@Transient
	@JsonIgnore
	public boolean isInativo() {
		return this.ativo == null ? true : !this.ativo;
	}
}
