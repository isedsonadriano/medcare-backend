package br.com.atendimento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

	
	@RequestMapping("/")
	public String inicio() {
		return "Aplicação de Atendimento";
	}
}
