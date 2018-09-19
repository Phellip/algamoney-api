package com.algaworks.algamoney.api.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.models.Pessoa;
import com.algaworks.algamoney.api.services.PessoaService;

@RequestMapping("/pessoas")
@RestController
public class PessoaResource {
	
	private PessoaService pessoaService;
	private ApplicationEventPublisher publisher;
	
	public PessoaResource(PessoaService pessoaService,ApplicationEventPublisher publisher) {
		this.pessoaService = pessoaService;
		this.publisher = publisher;
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> list = pessoaService.listarPessoas();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> listarPorCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaService.consultarPorCodigo(codigo);
		return ResponseEntity.ok(pessoa);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid Pessoa pessoa, 
			HttpServletResponse response) {
		
		Pessoa pessoaSave = pessoaService.salvar(Optional.of(pessoa));
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, pessoaSave.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> atualizar(@PathVariable Long codigo, @RequestBody @Valid Pessoa pessoa) {
		pessoaService.atualizarCompleto(codigo, pessoa);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{codigo}/ativo")
	public ResponseEntity<Void> atualizarAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedade(ativo, codigo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Long codigo) {
		pessoaService.deletarPorCodigo(codigo);
		return ResponseEntity.noContent().build();
	}
}
