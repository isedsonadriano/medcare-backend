package br.com.atendimento.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.atendimento.atendimento.GenericAtendimentoTest;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class AuthControllerTest extends GenericAtendimentoTest{

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveRetornarBadRequest() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\": \"invalido@trixti.com.br\", \"senha\":\"23232\"}";
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}

}
