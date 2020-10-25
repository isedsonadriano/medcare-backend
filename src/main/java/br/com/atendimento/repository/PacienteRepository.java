package br.com.atendimento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.atendimento.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Optional<Paciente> findByCodigo(Long codigo);

	@Query("SELECT COUNT(p.id) FROM Paciente p ")
	Integer capturarQuantidadePacientesNovosMes();

}
