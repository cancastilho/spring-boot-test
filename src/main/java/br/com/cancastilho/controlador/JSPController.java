package br.com.cancastilho.controlador;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class JSPController {

	@GetMapping("/index")
	public String index(Map<String, Object> model) {
		return "welcome";
	}

}