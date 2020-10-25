package br.com.atendimento.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.atendimento.domain.enuns.AtendimentoStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@NamedEntityGraph(
		  name = "atendimento-findAll",
		  attributeNodes = {
		    @NamedAttributeNode("medico"),
		    @NamedAttributeNode("paciente")
		  }
)

@NamedEntityGraphs({
@NamedEntityGraph(
        name = "atendimento-findById",
        attributeNodes = {
            @NamedAttributeNode(value = "procedimentos", subgraph = "procedimentosGraph"),
            @NamedAttributeNode("medico"),
		    @NamedAttributeNode("paciente"),
        },
        subgraphs = {
            @NamedSubgraph(
                    name = "procedimentosGraph",
                    attributeNodes = {
                        @NamedAttributeNode("procedimento")
                    }
            )
        }
    )
})

public class Atendimento {

	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataAlteracao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Medico medico;
	
	private String tipo;
	private String tipoAtendimento;
	
	@Enumerated(EnumType.STRING)
	private AtendimentoStatus status;
	
	@OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<AtendimentoProcedimento> procedimentos;
	
	private String observacoes;
	
	private BigDecimal valor;
	
	public Atendimento(Long id) {
		this.id = id;
	}
	
	@Deprecated
	public Atendimento() {
		super();
	}
	
	@PrePersist()
	public void prePersist() {
		this.status = AtendimentoStatus.ANALISE;
	}

	public void calcularValor() {
		this.valor = BigDecimal.ZERO;
		this.procedimentos.forEach(p ->{
			this.valor = this.valor.add(p.getProcedimento().getValor());
		});
	}

}
