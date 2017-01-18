package br.com.cancastilho.controlador;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cancastilho.config.AppConfig;

@RestController
@RequestMapping("/email")
@EnableConfigurationProperties(AppConfig.class)
public class EmailControlador {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private AppConfig config;

	// mapeia
	// http://dominio/email/enviar?de=xyz&destinatario=xyz&assunto=xyz&mensagem=xyz&anexo=true
	@GetMapping("/enviar")
	public boolean enviar(@RequestParam("de") String de, @RequestParam("para") String para,
			@RequestParam("assunto") String assunto, @RequestParam("mensagem") String texto,
			@RequestParam(name = "anexo", required = false, defaultValue = "false") boolean comAnexo) {

		if (comAnexo) {
			MimeMessage email = mailSender.createMimeMessage();

			try {
				MimeMessageHelper helper = new MimeMessageHelper(email, true);
				helper.setFrom(de);
				helper.setTo(para);
				helper.setSubject(assunto);
				helper.setText(texto);
				FileSystemResource file = new FileSystemResource("C:\\windows-version.txt");
				helper.addAttachment(file.getFilename(), file);
				mailSender.send(email);
				return true;
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				return false;
			}
		} else {
			SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom(de);
			email.setTo(para);
			email.setSubject(assunto);
			email.setText(texto);
			try {
				mailSender.send(email);
				return true;
			} catch (MailException ex) {
				System.err.println(ex.getMessage());
				return false;
			}
		}

	}

}
