package br.com.cancastilho;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.repositorio.ContribuinteRepositorio;
import br.com.cancastilho.repositorio.UserContribuinte;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ComponentScan(basePackages = { "br.com.cancastilho" })
public class ContribuinteRepositorioTest {

	@Autowired
	private ContribuinteRepositorio repositorio;

	// @Test
	public void testar_relacionamento_one_to_many() {
		Contribuinte findOne = repositorio.findOne(1L);
		System.out.println(findOne);
	}

	// @Test
	public void testar_relacionamento_one_to_many_find_by_nome() {
		List<Contribuinte> findByNome = repositorio.findByNome("CARLOS AUGUSTO NANTES");
		System.out.println(findByNome);
	}

	// @Test
	public void testar_projecao_spring_data() {
		// Apenas getNome disponível
		UserContribuinte findById = repositorio.findById(1L);
		System.out.println(findById);
	}

	@Test
	public void testar_contribuinte_repositorio_custom_impl() {
		// Apenas getNome disponível
		Contribuinte buscarPorId = repositorio.buscarPorId(1L);
		System.out.println(buscarPorId);
	}
}
