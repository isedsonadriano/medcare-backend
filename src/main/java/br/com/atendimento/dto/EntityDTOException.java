package br.com.atendimento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EntityDTOException {

	private String campo;
	private String erro;
	
	public EntityDTOException(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
}
