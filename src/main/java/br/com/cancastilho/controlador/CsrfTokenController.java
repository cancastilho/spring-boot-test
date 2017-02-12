package br.com.cancastilho.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/csrf")
public class CsrfTokenController {

	// @Autowired
	// private HttpSessionCsrfTokenRepository csrfRepository;
	//
	// @RequestMapping("/gerarToken")
	// public @ResponseBody String gerarToken(HttpServletRequest request) {
	// return csrfRepository.generateToken(request).getToken();
	// }
}
