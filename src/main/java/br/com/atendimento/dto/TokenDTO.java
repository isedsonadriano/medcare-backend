package br.com.atendimento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTO {

	private String token;
	private String tipo;

	public TokenDTO(String token, String tipo) {
		this.tipo = tipo;
		this.token = token;
	}

}
