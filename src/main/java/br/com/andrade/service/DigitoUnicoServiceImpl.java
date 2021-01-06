package br.com.andrade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.andrade.domain.entity.DigitoUnico;
import br.com.andrade.domain.entity.Usuario;
import br.com.andrade.domain.resource.DigitoUnicoResource;
import br.com.andrade.domain.resource.DigitoUnicoUsuarioResource;
import br.com.andrade.repository.DigitoUnicoRepository;
import br.com.andrade.repository.DigitoUnicoService;
import br.com.andrade.repository.UsuarioRepository;

@Service
public class DigitoUnicoServiceImpl implements DigitoUnicoService {

	private DigitoUnicoRepository digitoUnicoRepository;

	private UsuarioRepository usuarioRepository;

	public DigitoUnicoServiceImpl(DigitoUnicoRepository digitoUnicoRepository, UsuarioRepository usuarioRepository) {
		this.digitoUnicoRepository = digitoUnicoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Integer calcular(String numero) {
		return calcularDigitoUnico(numero);
	}

	@Override
	public DigitoUnicoResource salvar(DigitoUnicoResource digitoUnicoResource, Integer digitoUnico) {
		Usuario usuario = usuarioRepository.findById(digitoUnicoResource.getIdUsuario()).map(user -> user).orElse(null);
		return DigitoUnicoResource.toResource(
				digitoUnicoRepository.save(DigitoUnicoResource.toDomain(digitoUnicoResource, digitoUnico, usuario)));
	}

	@Override
	public String concatenarNumeroCalcular(String numero, Integer numeroParaConcatenar) {
		StringBuilder sb = new StringBuilder(numero);
		IntStream.range(1, numeroParaConcatenar).forEach(n -> sb.append(numero));
		return sb.toString();
	}
	
	@Override
	public List<DigitoUnicoUsuarioResource> pesquisarDigitosUnicosPorUsuario(Long id) {
		Usuario usuario = usuarioRepository.findById(id).map(user -> user).orElse(null);
		if(usuario != null) {
			DigitoUnico digitoUnico = new DigitoUnico();
			digitoUnico.setUsuario(usuario);
			return digitoUnicoRepository.findAll(Example.of(digitoUnico)).stream().map(d -> {
				return DigitoUnicoUsuarioResource.toResource(d.getId(), d.getDigitoUnico(), d.getNumeroCalculado(), d.getUsuario().getId());
			}).collect(Collectors.toList());
			
		}else {
			return new ArrayList<>();
		}
		
	}

	private Integer calcularDigitoUnico(String numero) {
		if (numero.length() == 1) {
			return Integer.parseInt(numero);
		} else {
			return numero.toString().chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList())
					.stream().mapToInt(n -> Integer.parseInt(n)).sum();
		}

	}
}