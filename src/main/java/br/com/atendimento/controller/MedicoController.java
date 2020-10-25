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

import br.com.atendimento.domain.Medico;
import br.com.atendimento.dto.MedicoDTO;
import br.com.atendimento.service.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController extends GenericController{

	@Autowired
	private MedicoService medicoService;

	@GetMapping
	public List<MedicoDTO> listar(@PageableDefault(sort="id", direction = Direction.DESC) Pageable pageable) {
		 Page<Medico> medicos = medicoService.listar(pageable);
		return medicos.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> catpurar(@PathVariable Long id) {
		Optional<Medico> medico = medicoService.capturar(id);
		if (medico.isPresent()) {
			return ResponseEntity.ok(convertToDto(medico.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<MedicoDTO> cadastrar(@RequestBody @Valid MedicoDTO medicoDto, UriComponentsBuilder uriBuilder) {
		Medico medico = this.modelMapper.map(medicoDto, Medico.class);
		this.medicoService.cadastrar(medico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(convertToDto(medico));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MedicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MedicoDTO medicoDto) {
		Medico medico = this.modelMapper.map(medicoDto, Medico.class);
		medico.setId(id);
		this.medicoService.atualizar(medico);
		return ResponseEntity.ok(convertToDto(medico));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.medicoService.deletar(id);
		return ResponseEntity.ok().build();
	}

	private MedicoDTO convertToDto(Medico medico) {
		MedicoDTO postDto = modelMapper.map(medico, MedicoDTO.class);
		return postDto;
	}
}
