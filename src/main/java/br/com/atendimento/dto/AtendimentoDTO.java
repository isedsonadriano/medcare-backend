package br.com.atendimento.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class AtendimentoDTO {

	private Long id;
	private LocalDateTime dataCadastro;
	private PacienteDTO paciente;
	private MedicoDTO medico;
	private List<ProcedimentoDTO> procedimentos;
	private String observacoes;
	private String tipo;
	private String tipoAtendimento;
	private String status;
	private String valor;

}
