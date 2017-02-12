package br.com.cancastilho.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.repositorio.ContribuinteRepositorio;

@RestController
@RequestMapping("contribuintes")
public class ContribuinteControlador {

	@Autowired
	private ContribuinteRepositorio repositorio;

	@GetMapping("listar1")
	public List<Contribuinte> indice(@RequestParam(name = "page", required = false, defaultValue = "0") int pagina,
			@RequestParam(name = "limit", required = false, defaultValue = "50") int limite) {
		if (pagina < 0) {
			pagina = 0;
		}

		Page<Contribuinte> paginaContribuintes = repositorio.findAll(new PageRequest(pagina, limite));

		return paginaContribuintes.getContent();
	}

	@GetMapping("listar")
	public List<Contribuinte> indice2(Pageable pageable) {
		int pagina = pageable.getPageNumber();
		int limite = pageable.getPageSize();
		Page<Contribuinte> paginaContribuintes = repositorio.findAll(new PageRequest(pagina, limite));

		return paginaContribuintes.getContent();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.repositorio.delete(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Contribuinte criar(@RequestBody Contribuinte c) {
		System.out.println(c);
		return this.repositorio.save(c);
	}
}
