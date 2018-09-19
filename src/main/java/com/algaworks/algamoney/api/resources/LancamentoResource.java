package com.algaworks.algamoney.api.resources;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;
import com.algaworks.algamoney.api.models.Lancamento;
import com.algaworks.algamoney.api.repositorys.filter.LancamentoFilter;
import com.algaworks.algamoney.api.services.LancamentoService;

@RequestMapping("/lancamentos")
@RestController
public class LancamentoResource {

	private LancamentoService lancamentoService;
	private ApplicationEventPublisher publisher;
	
	public LancamentoResource(LancamentoService lancamentoService, ApplicationEventPublisher publisher) {
		this.lancamentoService = lancamentoService;
		this.publisher = publisher;
	}

	@GetMapping
	public Page<Lancamento> pesquisarPaginado(LancamentoFilter filter,Pageable pageable) {
		return lancamentoService.consultarLancamentos(filter, pageable);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> listarPorCodigo(@PathVariable Optional<Long> codigo) {
		Lancamento lancamento = lancamentoService.consultarPorCodigo(codigo);
		return ResponseEntity.ok(lancamento);
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@RequestBody @Valid Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.salvar(Optional.of(lancamento));
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, lancamentoSalvo.getCodigo()));
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(lancamentoSalvo);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable Optional<Long> codigoOptional) {
		lancamentoService.deletarPorCodigo(codigoOptional);
		return ResponseEntity.noContent().build();
	}
}
