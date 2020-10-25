package br.com.atendimento.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class PacienteDTO {

	private Long id;
	private String nome;
	private String codigo;
	private LocalDateTime dataNascimento;
	private String cpf;
	private String email;
	private String telefone;
	

}
