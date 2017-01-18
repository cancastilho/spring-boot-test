package br.com.cancastilho.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cancastilho.config.AppConfig;

@RestController
@RequestMapping("/config")
@EnableConfigurationProperties(AppConfig.class)
public class ConfigControlador {

	@Autowired
	private AppConfig config;

	@GetMapping(path = "/listar")
	public AppConfig listar() {
		return config;
	}
}
