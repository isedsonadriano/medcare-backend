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

import br.com.atendimento.domain.Procedimento;
import br.com.atendimento.repository.ProcedimentoRepository;

@Service
@Transactional(readOnly = true)
public class ProcedimentoService extends GenericService {

	@Autowired
	private ProcedimentoRepository procedimentoRepository;

	@Transactional
	@CacheEvict(value = "procedimentos", allEntries = true)
	public void cadastrar(Procedimento procedimento) {
		try {
			procedimento.setDataCadastro(LocalDateTime.now());
			procedimentoRepository.save(procedimento);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional
	@CacheEvict(value = "procedimentos", allEntries = true)
	public void atualizar(Procedimento procedimento) {
		try {
			procedimento.setDataAlteracao(LocalDateTime.now());
			this.procedimentoRepository.save(procedimento);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	@CacheEvict(value = "procedimentos", allEntries = true)
	public void deletar(Long id) {
		try {
			procedimentoRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Optional<Procedimento> capturar(Long id) {
		return procedimentoRepository.findById(id);
	}

	@Cacheable(value = "procedimentos")
	public Page<Procedimento> listar(Pageable pageable) {
		return procedimentoRepository.findAll(pageable);
	}
	
}
