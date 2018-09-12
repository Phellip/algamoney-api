package com.algaworks.algamoney.api.builder;

import com.algaworks.algamoney.api.models.Categoria;

public class CategoriaBuilder {

	private Categoria categoria;
	
	private CategoriaBuilder() {
		//nao sera usado fora daqui.
	}

	public static CategoriaBuilder umaCategoria() {
		CategoriaBuilder builder = new CategoriaBuilder();
		builder.categoria = new Categoria();
		return builder;
	}
	
	public Categoria agora() {
		return this.categoria;
	}
}
