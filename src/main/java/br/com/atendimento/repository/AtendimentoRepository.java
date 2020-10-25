package br.com.atendimento.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.atendimento.domain.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	@EntityGraph(value = "atendimento-findAll", type = EntityGraphType.FETCH)
	Page<Atendimento> findAll(Pageable pageable);
	
	@EntityGraph(value = "atendimento-findById", type = EntityGraphType.FETCH)
	Optional<Atendimento> findById(Long id);

	@Query("SELECT COUNT(a.id) FROM Atendimento a")
	Integer capturarQuantidadeAtendimentoMes();

	@Query("SELECT COUNT(a.id) FROM Atendimento a WHERE a.status = 'NEGADO' ")
	Integer capturarQuantidadeAtendimentosNegadosMes();

	@Query("SELECT SUM(a.valor) FROM Atendimento a WHERE a.status IN ('AUTORIZADO', 'ANALISE') ")
	String capturarFaturamentoMes();
	

}
