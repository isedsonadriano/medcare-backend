package br.com.atendimento.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter 
public class AtendimentoProcedimento {

		@EmbeddedId
	    private AtendimentoProcedimentoId id;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @MapsId("atendimentoId")
	    private Atendimento atendimento;
	 
	    @ManyToOne(fetch = FetchType.LAZY)
	    @MapsId("procedimentoId")
	    private Procedimento procedimento;
	 
	    private Integer quantidade;
}
