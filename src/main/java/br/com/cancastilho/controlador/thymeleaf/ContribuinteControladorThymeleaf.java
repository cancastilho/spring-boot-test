package br.com.cancastilho.controlador.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cancastilho.modelo.Contribuinte;
import br.com.cancastilho.repositorio.ContribuinteRepositorio;

@Controller
@RequestMapping("/contribuinte")
public class ContribuinteControladorThymeleaf {

	private final ContribuinteRepositorio contribuinteRepositorio;

	@Autowired
	public ContribuinteControladorThymeleaf(ContribuinteRepositorio cr) {
		this.contribuinteRepositorio = cr;
	}

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView model = new ModelAndView("/contribuintes/listar");
		List<Contribuinte> contribuintes = contribuinteRepositorio.findAll();
		model.addObject("contribuintes", contribuintes);
		return model;
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView model = new ModelAndView("/contribuintes/novo");
		model.addObject("contribuinte", new Contribuinte());
		return model;
	}

	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("/contribuintes/editar");
		Contribuinte contribuinte = contribuinteRepositorio.findById(id);
		model.addObject("contribuinte", contribuinte);
		return model;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(Contribuinte contribuinte) {
		ModelAndView model = new ModelAndView("redirect:/contribuinte/listar");
		contribuinteRepositorio.save(contribuinte);
		return model;
	}

	@GetMapping("/{id}/excluir")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("redirect:/contribuinte/listar");
		contribuinteRepositorio.delete(id);
		return model;
	}

}
