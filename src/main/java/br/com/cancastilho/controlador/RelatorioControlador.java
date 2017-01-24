package br.com.cancastilho.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cancastilho.util.GeradorDePdf;

@RestController
@RequestMapping("/relatorio")
public class RelatorioControlador {

	@Autowired
	private GeradorDePdf geradorDePdf;

	@GetMapping("/exemplo")
	public ResponseEntity<byte[]> relatorioExemplo(@RequestParam("titulo") String titulo) {
		Map<String, Object> parametrosRelatorio = new HashMap<>();
		parametrosRelatorio.put("titulo", titulo);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		byte[] bytesDoPdf;
		try {
			String caminhoRelatorio = getClass().getClassLoader().getResource("relatorios/Wood.jasper").getPath();
			bytesDoPdf = geradorDePdf.gerarBytesDoPdf(caminhoRelatorio, parametrosRelatorio);
			header.setContentLength(bytesDoPdf.length);
			return new ResponseEntity<byte[]>(bytesDoPdf, header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<byte[]>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
