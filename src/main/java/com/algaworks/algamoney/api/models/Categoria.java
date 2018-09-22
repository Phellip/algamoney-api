package com.algaworks.algamoney.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categoria")
@NoArgsConstructor
@EqualsAndHashCode(of= {"codigo"})
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long codigo;
	
	@NotBlank
	@Size(min=3,max=20)
	@Setter @Getter
	private String nome;
	
	public Categoria(String nome) {
		this.nome = nome;
	}
}
