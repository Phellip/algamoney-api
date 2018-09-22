package com.algaworks.algamoney.api;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlgamoneyApiApplicationTests {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@LocalServerPort
	protected int randomServerPort;
}
