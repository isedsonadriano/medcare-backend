package br.com.atendimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.atendimento.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
