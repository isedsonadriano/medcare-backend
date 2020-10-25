package br.com.atendimento.vo;

public class Cpf {

	private String numero;
	
	@Deprecated
	public Cpf() {
	}

	public Cpf(String numero) {
		setNumero(numero);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
