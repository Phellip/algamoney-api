package com.algaworks.algamoney.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.models.Categoria;
import com.algaworks.algamoney.api.services.CategoriaService;

@RequestMapping("/categorias")
@RestController
public class CategoriaResource {

	private CategoriaService categoriaService;
	private ApplicationEventPublisher publisher;
	
	public CategoriaResource(CategoriaService categoriaService,ApplicationEventPublisher publisher) {
		this.categoriaService = categoriaService;
		this.publisher = publisher;
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> list = categoriaService.consultarCategorias();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> listarPorCodigo(@PathVariable Long codigo) {
		Categoria categoria = categoriaService.consultarPorCodigo(codigo);
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody @Valid Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.salvar(categoria);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, categoriaSalva.getCodigo()));
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@PathVariable Long codigo,  @RequestBody @Valid Categoria categoria) {
		categoriaService.atualizar(codigo, categoria);
		
		return ResponseEntity
				.noContent()
				.build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		categoriaService.deletarPorCodigo(codigo);
		
		return ResponseEntity
				.noContent()
				.build();
	}
	
}
