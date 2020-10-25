package br.com.atendimento.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atendimento.domain.Atendimento;
import br.com.atendimento.repository.AtendimentoRepository;

@Service
@Transactional(readOnly = true)
public class AtendimentoService extends GenericService {

	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	@Autowired
	private ProcedimentoService procedimentoService;

	@Transactional
	public void cadastrar(Atendimento atendimento) {
		try {
			atendimento.setDataCadastro(LocalDateTime.now());
			valorizarAtendimento(atendimento);
			atendimentoRepository.save(atendimento);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void valorizarAtendimento(Atendimento atendimento) {
		atendimento.getProcedimentos().forEach(p ->{
			p.setAtendimento(atendimento);
			p.setProcedimento(this.procedimentoService.capturar(p.getProcedimento().getId()).get());
		});
		atendimento.calcularValor();
	}

	@Transactional
	public void atualizar(Atendimento atendimento) {
		try {
			Atendimento atendimentoAtualizar = atendimentoRepository.getOne(atendimento.getId());
			atendimentoAtualizar.setDataAlteracao(LocalDateTime.now());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Transactional
	public void deletar(Long id) {
		try {
			atendimentoRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Optional<Atendimento> capturar(Long id) {
		return atendimentoRepository.findById(id);
	}


	@Transactional(readOnly = true)
	public Page<Atendimento> listar(Pageable pageable) {
		return atendimentoRepository.findAll(pageable);
	}

	public Integer capturarQuantidadeAtendimentoMes() {
		return atendimentoRepository.capturarQuantidadeAtendimentoMes();
	}

	public Integer capturarQuantidadeAtendimentosNegadosMes() {
		return atendimentoRepository.capturarQuantidadeAtendimentosNegadosMes();
	}

	public String capturarFaturamentoMes() {
		return atendimentoRepository.capturarFaturamentoMes();
	}

	
}
