package br.com.atendimento.atendimento;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.atendimento.domain.Atendimento;
import br.com.atendimento.repository.AtendimentoRepository;

/*@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = AtendimentoApplication.class)*/
public class AtendimentoRepositoryTest extends GenericAtendimentoTest {

	@Autowired
	private AtendimentoRepository repository;

	@Test
	public void deveCarregarAtendimentoPorId() {
		Optional<Atendimento> atendimento = this.repository.findById(1L);
		assertEquals(new Long(1L), atendimento.get().getId());
	}
}
