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

import br.com.atendimento.domain.Paciente;
import br.com.atendimento.dto.PacienteDTO;
import br.com.atendimento.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController extends GenericController{

	@Autowired
	private PacienteService pacienteService;

	@GetMapping
	public List<PacienteDTO> listar(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
		Page<Paciente> pacientes = pacienteService.listar(pageable);
		return pacientes.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> catpurar(@PathVariable Long id) {
		Optional<Paciente> paciente = pacienteService.capturar(id);
		if (paciente.isPresent()) {
			return ResponseEntity.ok(convertToDto(paciente.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> catpurarPorCodigo(@PathVariable Long codigo) {
		Optional<Paciente> paciente = pacienteService.capturarPorCodigo(codigo);
		if (paciente.isPresent()) {
			return ResponseEntity.ok(convertToDto(paciente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PacienteDTO> cadastrar(@RequestBody @Valid PacienteDTO pacienteDto, UriComponentsBuilder uriBuilder) {
		Paciente paciente = this.modelMapper.map(pacienteDto, Paciente.class);		
		this.pacienteService.cadastrar(paciente);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(convertToDto(paciente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PacienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PacienteDTO pacienteDto) {
		Paciente paciente = this.modelMapper.map(pacienteDto, Paciente.class);
		paciente.setId(id);
		this.pacienteService.atualizar(paciente);
		return ResponseEntity.ok(convertToDto(paciente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.pacienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/quantidade/mes")
	public ResponseEntity<?> capturarQuantidadePacientesNovosMes() {
		Integer quantidade = pacienteService.capturarQuantidadePacientesNovosMes();
		return ResponseEntity.ok(quantidade);
	}
	
	private PacienteDTO convertToDto(Paciente paciente) {
		PacienteDTO postDto = modelMapper.map(paciente, PacienteDTO.class);
		return postDto;
	}
}
