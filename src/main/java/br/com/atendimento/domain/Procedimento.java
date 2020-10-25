package br.com.atendimento.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter 
public class Procedimento {

	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	//@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Integer codigo;
	
	private BigDecimal valor;
	
	public Procedimento(Long id) {
		this.id = id;
	}
	
	@Deprecated
	public Procedimento() {
		super();
	}

}
