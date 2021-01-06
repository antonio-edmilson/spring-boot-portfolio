package br.com.andrade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.andrade.domain.resource.UsuarioResource;
import br.com.andrade.repository.UsuarioRepository;
import br.com.andrade.repository.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UsuarioResource salvar(UsuarioResource usuarioResource) {
		return UsuarioResource.toResource(usuarioRepository.save(UsuarioResource.toDomain(usuarioResource)));
	}

	@Override
	public UsuarioResource alterar(UsuarioResource usuarioResource) {
		return usuarioRepository.findById(usuarioResource.getId()).map(user -> {
			user.setNome(usuarioResource.getNome());
			user.setEmail(usuarioResource.getEmail());
			user.setIdade(usuarioResource.getIdade());
			return UsuarioResource.toResource(usuarioRepository.save(user));
		}).orElse(null);
	}

	@Override
	public ResponseEntity<?> deletar(Long id) {
		return usuarioRepository.findById(id).map(user -> {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok("Registro removido com sucesso");
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public List<UsuarioResource> pesquisar() {
		return usuarioRepository.findAll().stream().map(user -> UsuarioResource.toResource(user))
				.collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<UsuarioResource> pesquisarPorId(Long id) {
		return usuarioRepository.findById(id).map(user -> ResponseEntity.ok().body(UsuarioResource.toResource(user)))
				.orElse(ResponseEntity.notFound().build());
	}
}
