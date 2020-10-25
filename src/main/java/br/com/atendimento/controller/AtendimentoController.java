package br.com.atendimento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.atendimento.domain.Atendimento;
import br.com.atendimento.dto.AtendimentoDTO;
import br.com.atendimento.service.AtendimentoService;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController extends GenericController{

	@Autowired
	private AtendimentoService atendimentoService;

	@GetMapping
	public List<AtendimentoDTO> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 Page<Atendimento> atendimentos = atendimentoService.listar(pageable);
		return atendimentos.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> catpurar(@PathVariable Long id) {
		Optional<Atendimento> atendimento = atendimentoService.capturar(id);
		if (atendimento.isPresent()) {
			return ResponseEntity.ok(convertToDto(atendimento.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/quantidade/mes")
	public ResponseEntity<?> capturarQuantidadeAtendimentoMes() {
		Integer quantidade = atendimentoService.capturarQuantidadeAtendimentoMes();
		return ResponseEntity.ok(quantidade);
	}
	
	@GetMapping("/faturamento/mes")
	public ResponseEntity<?> capturarFaturamentoMes() {
		String faturamento = atendimentoService.capturarFaturamentoMes();
		return ResponseEntity.ok(faturamento);
	}
	
	@GetMapping("/quantidade/negados")
	public ResponseEntity<?> capturarQuantidadeAtendimentosNegadosMes() {
		Integer quantidade = atendimentoService.capturarQuantidadeAtendimentosNegadosMes();
		return ResponseEntity.ok(quantidade);
	}
	
	@PostMapping
	public ResponseEntity<AtendimentoDTO> cadastrar(@RequestBody @Valid AtendimentoDTO atendimentoDto, UriComponentsBuilder uriBuilder) {
		Atendimento atendimento = this.modelMapper.map(atendimentoDto, Atendimento.class);
		this.atendimentoService.cadastrar(atendimento);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(atendimento.getId()).toUri();
		return ResponseEntity.created(uri).body(convertToDto(atendimento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AtendimentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtendimentoDTO atendimentoDto, UriComponentsBuilder uriBuilder) {
		Atendimento atendimento = this.modelMapper.map(atendimentoDto, Atendimento.class);
		atendimento.setId(id);
		this.atendimentoService.atualizar(atendimento);
		return ResponseEntity.ok(convertToDto(atendimento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.atendimentoService.deletar(id);
		return ResponseEntity.ok().build();
	}

	private AtendimentoDTO convertToDto(Atendimento atendimento) {
		AtendimentoDTO postDto = modelMapper.map(atendimento, AtendimentoDTO.class);
		return postDto;
	}
}
