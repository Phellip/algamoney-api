package com.algaworks.algamoney.api.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.algaworks.algamoney.api.models.Categoria;

public class Categorias {

	private List<Categoria> categorias;
	
	public Categorias() {
		this.categorias = new ArrayList<>();
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
} 
