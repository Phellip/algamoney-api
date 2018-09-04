package com.algaworks.algamoney.api.events;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ResourceCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = -9180649398173515233L;
	
	@Getter
	private HttpServletResponse response;
	
	@Getter
	private Long codigo;

	public ResourceCreatedEvent(Object source, HttpServletResponse response,Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

}
