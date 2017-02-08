package br.com.cancastilho.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GeradorDePdf {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GeradorDePdf(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public byte[] gerarBytesDoPdf(String caminhoRelatorioJasper, Map<String, Object> parametrosRelatorio)
			throws SQLException, JRException {
		// se não passar nenhum datasource o relatório sairá em branco.Por isso
		// emptyDatasource é passado.
		JREmptyDataSource emptyDataSource = new JREmptyDataSource();
		byte[] bytesDoPdf = JasperRunManager.runReportToPdf(caminhoRelatorioJasper, parametrosRelatorio,
				emptyDataSource);
		return bytesDoPdf;
	}

	public byte[] gerarBytesDoPdf(String caminhoRelatorioJasper, Map<String, Object> parametrosRelatorio,
			JRBeanCollectionDataSource datasource) throws SQLException, JRException {
		Connection conexao = jdbcTemplate.getDataSource().getConnection();
		parametrosRelatorio.put("connection", conexao);
		byte[] bytesDoPdf = JasperRunManager.runReportToPdf(caminhoRelatorioJasper, parametrosRelatorio, datasource);
		return bytesDoPdf;
	}

}
