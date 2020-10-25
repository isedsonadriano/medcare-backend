package br.com.atendimento.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class AtendimentoProcedimentoId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "atendimento_id")
	private Long atendimentoId;

	@Column(name = "procedimento_id")
	private Long procedimentoId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atendimentoId == null) ? 0 : atendimentoId.hashCode());
		result = prime * result + ((procedimentoId == null) ? 0 : procedimentoId.hashCode());
		return result;
	}
	
	public AtendimentoProcedimentoId() {
		super();
	}
	
	public AtendimentoProcedimentoId(Long atendimentoId, Long procedimentoId) {
		super();
		this.atendimentoId = atendimentoId;
		this.procedimentoId = procedimentoId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AtendimentoProcedimentoId other = (AtendimentoProcedimentoId) obj;
		if (atendimentoId == null) {
			if (other.atendimentoId != null)
				return false;
		} else if (!atendimentoId.equals(other.atendimentoId))
			return false;
		if (procedimentoId == null) {
			if (other.procedimentoId != null)
				return false;
		} else if (!procedimentoId.equals(other.procedimentoId))
			return false;
		return true;
	}

}
