package br.com.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atendimento.domain.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
