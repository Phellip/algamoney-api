package com.algaworks.algamoney.api.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;


@Configuration
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
public class ZalandoConfig {

	@Bean
	ProblemModule problemModule() {
		return new ProblemModule();
	}
	
	@Bean
	ConstraintViolationProblemModule constraintViolationProblemModule() {
		return new ConstraintViolationProblemModule();
	}
}
