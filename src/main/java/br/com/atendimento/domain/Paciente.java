package br.com.atendimento.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.atendimento.vo.Cpf;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter 
public class Paciente {

	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
//	@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	private Long codigo;
	
	private String nome;
	
	private String telefone;
	
	private LocalDateTime dataNascimento;
	
	@Embedded
	@AttributeOverride(name="numero", column=@Column(name="cpf"))
	private Cpf cpf;
	
	private String email;
	
	@Deprecated
	public Paciente() {
		super();
	}
	
	public void setCpf(String numero) {
		this.cpf = new Cpf(numero);
	}
	
}
