package com.algaworks.algamoney.api.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@ControllerAdvice
public class AlgamoneyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messages;
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgCliente = messages.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDev = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(msgCliente,msgDev));
		
		return  handleExceptionInternal(ex,erros,headers,HttpStatus.BAD_REQUEST,request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	protected ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, 
			WebRequest request) {
		
		String msgCliente = messages.getMessage("mensagem.nao-encontrado", null, LocaleContextHolder.getLocale());
		String msgDev = ex.getMessage();
		
		List<Erro> erros = Arrays.asList(new Erro(msgCliente,msgDev));
		
		return  handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
	}
	
	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Locale locale = LocaleContextHolder.getLocale();
		List<Erro> erros = fieldErrors
							.stream()
							.map(fieldError -> {
								String msgClient = messages.getMessage(fieldError, locale);
								String msgDev = bindingResult.toString();
								return new Erro(msgClient,msgDev);
							})
							.collect(Collectors.toList());
		
		return erros;
	}
	
	@AllArgsConstructor
	public static class Erro {
		
		@Getter
		String msgCliente;
		
		@Getter
		String msgDesenvolvedor;
	}
}
