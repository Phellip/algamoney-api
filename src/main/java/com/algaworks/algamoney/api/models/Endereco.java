package com.algaworks.algamoney.api.models;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Embeddable
@Data
public class Endereco {
	
	@NotBlank
	@Size(min=6,max=60)
	private String logradouro;
	
	@NotBlank
	@Size(min=1,max=6)
	private String numero;
	
	@Size(min=5,max=100)
	@Basic(optional=true)
	private String complemento;
	
	@NotBlank
	@Size(min=5,max=60)
	private String bairro;
	
	@NotBlank
	@Size(min=5,max=20)
	private String cep;
	
	@NotBlank
	@Size(min=5,max=30)
	private String cidade;
	
	@NotBlank
	@Size(min=2,max=4)
	private String estado;
}
