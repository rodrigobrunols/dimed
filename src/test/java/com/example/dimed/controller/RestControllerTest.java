package com.example.dimed.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dimed.controller.LinhasController;
import com.example.dimed.controller.integracao.IntegracaoDataPoaController;

@SpringBootTest
public class RestControllerTest {

	@Autowired
	private LinhasController linhaController;
	
	@Autowired
	private ItinerariosController itinerarioController;
	
	@Autowired
	private IntegracaoDataPoaController integracaoDataPoaController;
	

	@Test
	public void contextLoads() throws Exception {
		assertThat(linhaController).isNotNull();
		
		assertThat(itinerarioController).isNotNull();
		
		assertThat(integracaoDataPoaController).isNotNull();
		
	}
}