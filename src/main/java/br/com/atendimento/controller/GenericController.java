package br.com.atendimento.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600L)
public class GenericController {

	@Autowired
	protected ModelMapper modelMapper;
	
}
