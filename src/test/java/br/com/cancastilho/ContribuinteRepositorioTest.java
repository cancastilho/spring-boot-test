package br.com.cancastilho;

import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.modelo.Imovel;
import br.com.cancastilho.repositorio.ContribuinteRepositorio;
import br.com.cancastilho.repositorio.ImovelRepositorio;
import br.com.cancastilho.util.GeradorDePdf;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ComponentScan(basePackages = { "br.com.cancastilho" })
public class ContribuinteRepositorioTest {

	@Autowired
	private ContribuinteRepositorio repositorio;

	@Autowired
	private ImovelRepositorio imovelRepositorio;

	@Autowired
	private GeradorDePdf geradorDePdf;

	@Value(value = "classpath:/relatorios/Wood.jasper")
	private Resource relatorioJasper;

	@Value(value = "classpath:/relatorios/Wood.jpg")
	private Resource imagemRelatorio;

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
		Contribuinte findById = repositorio.findById(1L);
		System.out.println(findById);
	}

	// @Test
	public void testar_contribuinte_repositorio_custom_impl() {
		// Apenas getNome disponível
		Contribuinte buscarPorId = repositorio.buscarPorId(1L);
		System.out.println(buscarPorId);
	}

	// @Test
	public void testar_salvar_imovel_do_contribuinte() {
		// Apenas getNome disponível
		Contribuinte c = repositorio.buscarPorId(1L);
		int qtdImoveisRetornada = c.getImoveis().size();
		int qtdImoveisEsperada = 4;
		assertEquals(qtdImoveisEsperada, qtdImoveisRetornada);
		Imovel i = new Imovel();
		i.setContribuinteId(c.getId());
		i.setTipo("Casa");
		Imovel save = imovelRepositorio.save(i);
		c = repositorio.findOne(1l);
		qtdImoveisRetornada = c.getImoveis().size();
		qtdImoveisEsperada = 5;
		assertEquals(qtdImoveisEsperada, qtdImoveisRetornada);
		System.out.println();
	}

	@Test
	public void testar_geracao_de_relatorio() throws IOException, SQLException, JRException {
		Contribuinte c = new Contribuinte();
		c.setId(1L);
		c.setBairro("Meu bairro");
		c.setComplemento("Um complemento");
		c.setEndereco("Rua alguma coisa");
		c.setNome("Nome do cara");
		Imovel i = new Imovel();
		i.setContribuinteId(c.getId());
		i.setId(1L);
		i.setTipo("Tipo do imovel 1");
		c.adicionarImovel(i);
		Imovel i2 = new Imovel();
		i2.setContribuinteId(c.getId());
		i2.setId(2L);
		i2.setTipo("Tipo do imovel 2");
		c.adicionarImovel(i2);
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(Arrays.asList(c));
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("titulo", "Titulo de teste");
		parametros.put("caminhoImagem", this.imagemRelatorio.getFile().getAbsolutePath());
		String jasperPath = this.relatorioJasper.getFile().getAbsolutePath();
		byte[] bytesDoRelatorio = geradorDePdf.gerarBytesDoPdf(jasperPath, parametros, datasource);
		FileOutputStream fos = new FileOutputStream("Wood.pdf");
		fos.write(bytesDoRelatorio);
		fos.close();
	}
}
