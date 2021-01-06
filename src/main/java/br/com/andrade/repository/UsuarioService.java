package br.com.andrade.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.com.andrade.domain.resource.UsuarioResource;

@Repository
public interface UsuarioService {
	UsuarioResource salvar(UsuarioResource usuarioResource);
	
	UsuarioResource alterar(UsuarioResource usuarioResource);
	
	ResponseEntity<?> deletar(Long id);

	List<UsuarioResource> pesquisar();

	ResponseEntity<UsuarioResource> pesquisarPorId(Long id);
}
