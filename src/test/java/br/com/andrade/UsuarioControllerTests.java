package br.com.andrade;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.andrade.domain.resource.UsuarioResource;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioControllerTests {
	private String uri = "/usuarios";

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testBuscarUsuarios() throws Exception {
		mvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	
	@Test
	void testCadastrarUsuario() throws Exception {
		UsuarioResource usuarioResource = new UsuarioResource(null, "Antonio Edmilson", "aedmilson10@gmail.com", 32);
		mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(usuarioResource))).andExpect(status().isOk());

	}
	
	@Test
	void testCadastrarUsuarioNomeObrigatorio() throws Exception {
		UsuarioResource usuarioResource = new UsuarioResource(null, "", "aedmilson10@gmail.com", 32);
		mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(usuarioResource))).andExpect(status().isBadRequest());

	}

	@Test
	void testCadastrarUsuarioEmailInvalido() throws Exception {
		UsuarioResource usuarioResource = new UsuarioResource(null, "Antonio Edmilson", "acom", 32);
		mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(usuarioResource))).andExpect(status().isBadRequest());

	}

}
