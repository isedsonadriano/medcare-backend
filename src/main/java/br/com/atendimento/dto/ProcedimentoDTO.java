package br.com.atendimento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class ProcedimentoDTO {

	private Long id;
	private Integer codigo;
	private String descricao;
	private String valor;
	private Integer quantidade;
	
}
