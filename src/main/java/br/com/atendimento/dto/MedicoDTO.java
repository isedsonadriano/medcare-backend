package br.com.atendimento.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class MedicoDTO {

	private Long id;
	private LocalDateTime dataCadastro;
	private String nome;
	private String crm;
	private String cpf;
	private String email;
	
}
