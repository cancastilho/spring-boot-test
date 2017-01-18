package br.com.cancastilho.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "app")
public class AppConfig {

	@Getter
	@Setter
	public static class Desenvolvedor {

		private String nome;

		private String email;

		private List<String> telefones = new ArrayList<String>();

	}

	@NotNull
	private String nome;

	private Desenvolvedor desenvolvedor;

}