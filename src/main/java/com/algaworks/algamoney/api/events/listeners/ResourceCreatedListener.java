package com.algaworks.algamoney.api.events.listeners;

import java.net.URI;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.events.ResourceCreatedEvent;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {

	@Override
	public void onApplicationEvent(ResourceCreatedEvent event) {

		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{codigo}")
					.buildAndExpand(event.getCodigo())
					.toUri();
		
		event.getResponse().setHeader("Location",uri.toASCIIString());
	}

}
