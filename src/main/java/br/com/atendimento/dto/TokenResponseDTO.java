package br.com.atendimento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

	private String token;
	private String usuario;

	public TokenResponseDTO(String token, String usuario) {
		this.usuario = usuario;
		this.token = token;
	}

}
