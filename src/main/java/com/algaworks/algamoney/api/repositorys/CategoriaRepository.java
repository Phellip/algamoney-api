package com.algaworks.algamoney.api.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
