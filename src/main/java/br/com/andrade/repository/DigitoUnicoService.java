package br.com.andrade.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.andrade.domain.resource.DigitoUnicoResource;
import br.com.andrade.domain.resource.DigitoUnicoUsuarioResource;

@Repository
public interface DigitoUnicoService {
	
	DigitoUnicoResource salvar(DigitoUnicoResource digitoUnicoResource, Integer digitoUnico);

	Integer calcular(String numero);

	String concatenarNumeroCalcular(String numero, Integer numeroParaConcatenar);
	
	List<DigitoUnicoUsuarioResource> pesquisarDigitosUnicosPorUsuario(Long id);
}
