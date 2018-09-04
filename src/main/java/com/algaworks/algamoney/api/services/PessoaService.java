package com.algaworks.algamoney.api.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.models.Pessoa;
import com.algaworks.algamoney.api.repositorys.PessoaRepository;

@Service
public class PessoaService {

	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
		
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa atualizarCompleto(Long codigo, Pessoa pessoaAtualizar) {
		Pessoa pessoaBD = consultarPorCodigo(codigo);
		BeanUtils.copyProperties(pessoaAtualizar, pessoaBD,"codigo");
		
		pessoaRepository.save(pessoaBD);
		
		return pessoaBD;
	}
	
	public Pessoa atualizarPropriedade(Boolean ativo, Long codigo) {
		Pessoa pessoaBD = consultarPorCodigo(codigo);
		pessoaBD.setAtivo(ativo);
		pessoaRepository.save(pessoaBD);
		
		return pessoaBD;
	}
	
	public void deletarPorCodigo(Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	/**
	 * @param codigo de uma pessoa existente no BD.
	 * @throws {@code EmptyResultDataAccessException} lançado caso não exista uma pessoa com
	 * o codigo informado.
	 * */
	public Pessoa consultarPorCodigo(Long codigo) {
		Pessoa pessoaBD = pessoaRepository.findOne(codigo);
		if(Objects.isNull(pessoaBD)) {
			throw new EmptyResultDataAccessException(1); // 1 representa a qntd de recurso esperado.
		}
		return pessoaBD;
	}
	
	/**
	 * @throws {@code EmptyResultDataAccessException} lançado caso não exista pessoas cadastradas.
	 * */
	public List<Pessoa> listarPessoas() {
		 List<Pessoa> list = pessoaRepository.findAll();
		 if(Objects.isNull(list) || list.isEmpty()) {
			 throw new EmptyResultDataAccessException(Integer.MAX_VALUE);
		 }
		 
		 return list;
	}
	
}
