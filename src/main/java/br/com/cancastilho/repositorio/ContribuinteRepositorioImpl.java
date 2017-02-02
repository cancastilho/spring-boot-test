package br.com.cancastilho.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.modelo.Imovel;

@Service
public class ContribuinteRepositorioImpl implements ContribuinteRepositorioCustom {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ContribuinteRepositorioImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Contribuinte buscarPorId(Long id) {
		BeanPropertyRowMapper<Contribuinte> mapperContribuinte = BeanPropertyRowMapper.newInstance(Contribuinte.class);
		Contribuinte contribuinte = this.jdbcTemplate.queryForObject("SELECT * FROM CONTRIBUINTE where id=?",
				mapperContribuinte, id);

		String sql = "SELECT * FROM IMOVEL where contribuinte_id=?";
		List<Imovel> imoveis = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Imovel.class),
				contribuinte.getId());

		contribuinte.setImoveis(imoveis);
		return contribuinte;
	}

}
