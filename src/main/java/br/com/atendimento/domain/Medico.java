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
public class Medico {

	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	//@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String crm;
	
	@NotNull
	private String email;
	
	@NotNull
	@Embedded
	@AttributeOverride(name="numero", column=@Column(name="cpf"))
	private Cpf cpf;
	
	public Medico(Long id) {
		this.id = id;
	}
	
	@Deprecated
	public Medico() {
		super();
	}
	
	public void setCpf(String numero) {
		this.cpf = new Cpf(numero);
	}
	

}
