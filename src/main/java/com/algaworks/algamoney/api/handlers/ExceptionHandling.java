package com.algaworks.algamoney.api.handlers;

import static com.algaworks.algamoney.api.handlers.AlgaMoneyProblemType.DATA_INTEGRITY_VIOLATION;
import static com.algaworks.algamoney.api.handlers.AlgaMoneyProblemType.EMPTY_RESULT_DATA;
import static com.algaworks.algamoney.api.handlers.AlgaMoneyProblemType.PESSOA_INEXISTENTE_INATIVA;
import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.NOT_FOUND;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import com.algaworks.algamoney.api.services.exception.PessoaInexistenteOuInativaException;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {
	
	@Autowired
	private MessageSource messages;
	
	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Problem> handlePessoaInexistenteOuInativaException(
			PessoaInexistenteOuInativaException ex, NativeWebRequest request) {
				
		HttpServletRequest httpServletRequest = converterToHttpServletRequest(request);
		
		ThrowableProblem problem = createThrowableProblem(
									PESSOA_INEXISTENTE_INATIVA.getUri(), 
									PESSOA_INEXISTENTE_INATIVA.getTitle(), 
									Status.BAD_REQUEST, 
									messages.getMessage("lancamento.pessoa-inativa-inexistente",null, LocaleContextHolder.getLocale()), 
									URI.create(httpServletRequest.getRequestURI()));
		
		return handleProblem(problem, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Problem> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex, NativeWebRequest request) {
		
		HttpServletRequest httpServletRequest = converterToHttpServletRequest(request);
		
		ThrowableProblem problem = createThrowableProblem(
									DATA_INTEGRITY_VIOLATION.getUri(),
									DATA_INTEGRITY_VIOLATION.getTitle(),
									BAD_REQUEST,
									"Exception thrown when an attempt to insert or update data results "
									+ "in violation of an integrity constraint",
									URI.create(httpServletRequest.getRequestURI()));
		
		return handleProblem(problem, request);
	}

	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	protected ResponseEntity<Problem> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, 
			NativeWebRequest request) {
		
		HttpServletRequest httpServletRequest = converterToHttpServletRequest(request);
		ThrowableProblem problem =  createThrowableProblem(
										EMPTY_RESULT_DATA.getUri(), 
										EMPTY_RESULT_DATA.getTitle(), 
										NOT_FOUND, ex.getMessage(), 
										URI.create(httpServletRequest.getRequestURI()));
		
		return  handleProblem(problem,request);
	}

	/**
	 * Problem Details - É uma maneira de transportar detalhes de erro legível por maquinas 
	 * dentro de uma resposta http de forma padronizada. 
	 * 
	 * @param type URI que identifica o problem type. Deve providenciar uma forma de leitura legível por humanos (html).
	 * @param title Um curto resumo humanamento legivel do problem type. Deve permanecer estável, uma vez definido não deve mudar.
	 * @param status Status HTTP do problem type.
	 * @param detail Uma expecífica explicação humanamente legivel para a ocorrência do problema.
	 * @param instance Uma URI que identifica a especifica ocorrencia do problema.
	 * 
	 * @return ThrowableProblem 
	 * */
	private ThrowableProblem createThrowableProblem(URI type,String title, Status status, String detail, URI instance) {
		return Problem
				.builder()
					.withType(type)
					.withTitle(title)
					.withStatus(status)
					.withDetail(detail)
					.withInstance(instance)
				.build();
	}
	
	private HttpServletRequest converterToHttpServletRequest(NativeWebRequest request) {
		return request.getNativeRequest(HttpServletRequest.class);
	}
}
