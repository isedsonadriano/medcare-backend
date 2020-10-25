package br.com.atendimento.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atendimento.domain.Medico;
import br.com.atendimento.repository.MedicoRepository;

@Service
@Transactional(readOnly = true)
public class MedicoService extends GenericService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Transactional
	@CacheEvict(value = "medicos", allEntries = true)
	public void cadastrar(Medico medico) {
		try {
			medico.setDataCadastro(LocalDateTime.now());
			medicoRepository.save(medico);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	@CacheEvict(value = "medicos", allEntries = true)
	public void atualizar(Medico medico) {
		try {
			medico.setDataAlteracao(LocalDateTime.now());
			this.medicoRepository.save(medico);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	@CacheEvict(value = "medicos", allEntries = true)
	public void deletar(Long id) {
		try {
			medicoRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Optional<Medico> capturar(Long id) {
		return medicoRepository.findById(id);
	}

	@Cacheable(value = "medicos")
	public Page<Medico> listar(Pageable pageable) {
		return medicoRepository.findAll(pageable);
	}
	
}
