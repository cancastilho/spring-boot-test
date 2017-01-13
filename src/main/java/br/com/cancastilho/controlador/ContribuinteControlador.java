package br.com.cancastilho.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.repositorio.ContribuinteRepositorio;

public class ContribuinteControlador {

	@Autowired
	private ContribuinteRepositorio repositorio;

	@GetMapping("/")
	public List<Contribuinte> indice(@RequestParam(name = "page", required = false, defaultValue = "0") int pagina,
			@RequestParam(name = "limit", required = false, defaultValue = "50") int limite) {
		if (pagina < 0) {
			pagina = 0;
		}

		Page<Contribuinte> paginaContribuintes = repositorio.findAll(new PageRequest(pagina, limite));

		return paginaContribuintes.getContent();
	}
}
