package br.com.cancastilho.controlador;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/csrf")
public class CsrfTokenController {

	@Autowired
	private HttpSessionCsrfTokenRepository csrfRepository;

	@RequestMapping("/gerarToken")
	public @ResponseBody String gerarToken(HttpServletRequest request) {
		return csrfRepository.generateToken(request).getToken();
	}
}
