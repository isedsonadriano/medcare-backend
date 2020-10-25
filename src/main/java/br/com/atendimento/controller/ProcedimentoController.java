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

import br.com.atendimento.domain.Procedimento;
import br.com.atendimento.dto.ProcedimentoDTO;
import br.com.atendimento.service.ProcedimentoService;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController extends GenericController{

	@Autowired
	private ProcedimentoService procedimentoService;

	@GetMapping
	public List<ProcedimentoDTO> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 Page<Procedimento> procedimentos = procedimentoService.listar(pageable);
		return procedimentos.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> catpurar(@PathVariable Long id) {
		Optional<Procedimento> procedimento = procedimentoService.capturar(id);
		if (procedimento.isPresent()) {
			return ResponseEntity.ok(convertToDto(procedimento.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<ProcedimentoDTO> cadastrar(@RequestBody @Valid ProcedimentoDTO procedimentoDto, UriComponentsBuilder uriBuilder) {
		Procedimento procedimento = this.modelMapper.map(procedimentoDto, Procedimento.class);
		this.procedimentoService.cadastrar(procedimento);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(procedimento.getId()).toUri();
		return ResponseEntity.created(uri).body(convertToDto(procedimento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProcedimentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProcedimentoDTO procedimentoDto) {
		Procedimento procedimento = this.modelMapper.map(procedimentoDto, Procedimento.class);
		procedimento.setId(id);
		this.procedimentoService.atualizar(procedimento);
		return ResponseEntity.ok(convertToDto(procedimento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.procedimentoService.deletar(id);
		return ResponseEntity.ok().build();
	}

	private ProcedimentoDTO convertToDto(Procedimento procedimento) {
		ProcedimentoDTO postDto = modelMapper.map(procedimento, ProcedimentoDTO.class);
		return postDto;
	}
}
