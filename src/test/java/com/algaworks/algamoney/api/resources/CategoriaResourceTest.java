package com.algaworks.algamoney.api.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algamoney.api.AlgamoneyApiApplicationTests;
import com.algaworks.algamoney.api.models.Categoria;
import com.algaworks.algamoney.api.repositorys.CategoriaRepository;

public class CategoriaResourceTest extends AlgamoneyApiApplicationTests {
	
	private String URI_PADRAO = "http://localhost:%d/%s";
	
	@Autowired
	private CategoriaRepository repository;
	
	private RestTemplate restTemplate;
	
	@Before
	public void setUP() {
		restTemplate = new RestTemplate();
	}
	
	@After
	public void after() {
		repository.deleteAll();
	}
	
	@Test
	public void deve_listar_um_registro() throws Exception {
		//cenario
		String URL = String.format(URI_PADRAO, randomServerPort,"/categorias");
		
		Categoria categoria1 = new Categoria("Lazer");
		repository.save(categoria1);
		repository.flush();
		//execucao
		ResponseEntity<List<Categoria>> response = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>(){});
		
		//validacao
		error.checkThat(response.getStatusCode(), is(HttpStatus.OK));
		error.checkThat(response.getBody().size(), is(1));
		error.checkThat(response.getBody().get(0).getCodigo(), notNullValue());
	}
	
	@Test
	public void deve_listar_3_registro() throws Exception {
		//cenario
		String URL = String.format(URI_PADRAO, randomServerPort,"/categorias");
		
		Categoria categoria1 = new Categoria("Lazer1");
		Categoria categoria2 = new Categoria("Lazer2");
		Categoria categoria3 = new Categoria("Lazer3");
		repository.save(categoria1);
		repository.save(categoria2);
		repository.save(categoria3);
		
		//execucao
		ResponseEntity<List<Categoria>> response = restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>(){});
	
		//validacao
		error.checkThat(response.getBody().size(), is(3));
		error.checkThat(response.getBody().get(0).getCodigo(), notNullValue());
		error.checkThat(response.getBody().get(1).getCodigo(), notNullValue());
		error.checkThat(response.getBody().get(2).getCodigo(), notNullValue());
		
	}
}
