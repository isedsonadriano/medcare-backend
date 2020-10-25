package br.com.atendimento.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atendimento.domain.Paciente;
import br.com.atendimento.repository.PacienteRepository;

@Service
@Transactional(readOnly = true)
public class PacienteService extends GenericService {

	@Autowired
	private PacienteRepository pacienteRepository;

	@Transactional
	public void cadastrar(Paciente paciente) {
		try {
			paciente.setDataCadastro(LocalDateTime.now());
			pacienteRepository.save(paciente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	public void atualizar(Paciente paciente) {
		try {		
			paciente.setDataAlteracao(LocalDateTime.now());
			pacienteRepository.save(paciente);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	public void deletar(Long id) {
		try {
			pacienteRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Optional<Paciente> capturar(Long id) {
		return pacienteRepository.findById(id);
	}


	public Page<Paciente> listar(Pageable pageable) {
		return pacienteRepository.findAll(pageable);
	}

	public Optional<Paciente> capturarPorCodigo(Long codigo) {
		return pacienteRepository.findByCodigo(codigo);
	}

	public Integer capturarQuantidadePacientesNovosMes() {
		return pacienteRepository.capturarQuantidadePacientesNovosMes();
	}
	
}
