package br.com.andrade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrade.domain.resource.ChavePublicaUsuarioResource;
import br.com.andrade.domain.resource.UsuarioResource;
import br.com.andrade.repository.UsuarioService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController {

	private static final long serialVersionUID = 7836706663420269559L;

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResource>> pesquisar() {
		return ResponseEntity.ok().body(usuarioService.pesquisar());
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<UsuarioResource> pesquisarPorId(Long id) {
		return usuarioService.pesquisarPorId(id);
	}

	@PostMapping
	public ResponseEntity<UsuarioResource> cadastrar(@RequestBody @Valid UsuarioResource usuarioResource) {
		return ResponseEntity.ok().body(usuarioService.salvar(usuarioResource));
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		return usuarioService.deletar(id);
	}

	@PutMapping
	public ResponseEntity<UsuarioResource> alterar(@RequestBody UsuarioResource usuarioResource) {
		UsuarioResource usuarioRsc = usuarioService.alterar(usuarioResource);
		return usuarioRsc != null ? ResponseEntity.ok().body(usuarioRsc) : ResponseEntity.notFound().build();
	}

}
