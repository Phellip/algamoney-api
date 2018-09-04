package com.algaworks.algamoney.api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.Categoria;
import com.algaworks.algamoney.api.repositorys.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;
	
	@Autowired
	public CategoriaService(CategoriaRepository repository) {
		this.categoriaRepository = repository;
		
	}
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaBD = categoriaRepository.findOne(codigo);
		if(Objects.isNull(categoriaBD)) {
			throw new EmptyResultDataAccessException(1); //qtd recurso esperado no findOne.
		}
		BeanUtils.copyProperties(categoria,categoriaBD,"codigo");
		
		categoriaRepository.save(categoriaBD);
		
		return categoriaBD;
	}
	
	public void deletarPorCodigo(Long codigo) {
		categoriaRepository.delete(codigo);
	}
	
	public List<Categoria> consultarCategorias() {
		List<Categoria> list = categoriaRepository.findAll();
		if(Objects.isNull(list) || list.isEmpty()) {
			throw new EmptyResultDataAccessException(Integer.MAX_VALUE);
		}
		
		return list;
	}

	public Categoria consultarPorCodigo(Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		if(Objects.isNull(categoria)) {
			throw new EmptyResultDataAccessException(1); 
		}
		
		return categoria;
	}
}
