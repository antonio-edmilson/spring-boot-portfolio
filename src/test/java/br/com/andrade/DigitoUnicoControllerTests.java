package br.com.andrade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.andrade.domain.resource.DigitoUnicoResource;
import br.com.andrade.repository.DigitoUnicoService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DigitoUnicoControllerTests {
	private String uriCalcular = "/digitosUnicos/calcular";

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private DigitoUnicoService digitoUnicoService;

	@Test
	void testCalcularDigitoUnicoSemUsuario() throws Exception {
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, "11", null);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isOk());
	}

	@Test
	void testCalcularDigitoUnicoComUsuario() throws Exception {
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, "11", 1l);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isOk());
	}

	@Test
	void testCalcularDigitoUnicoConcatenado() throws Exception {
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, "11", 2, 1l);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isOk());
	}

	@Test
	void testCalcularDigitoUnicoNumeroObrigatorio() throws Exception {
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, null, 2, 1l);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isBadRequest());
	}

	@Test
	void testCalcularDigitoUnicoNumeroMaiorZero() throws Exception {
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, "-1", 2, 1l);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isBadRequest());
	}
	
	@Test
	void testCalcularDigitoUnicoNumeroMaiorExpoente() throws Exception {
		String exp = String.valueOf(Math.pow(Double.valueOf(1), Double.valueOf(1000000)) + 1d);
		DigitoUnicoResource digitoUnicoResource = new DigitoUnicoResource(null, exp, 1, null);
		mvc.perform(post(uriCalcular).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(digitoUnicoResource))).andExpect(status().isBadRequest());
	}
	
	@Test
	void testCalcular(){
		String numeroConcatenado = digitoUnicoService.concatenarNumeroCalcular("9785", 4);
		assertEquals(digitoUnicoService.calcular(numeroConcatenado), 116);
		assertEquals(digitoUnicoService.calcular("9785"), 29);
		
		
	}


}
