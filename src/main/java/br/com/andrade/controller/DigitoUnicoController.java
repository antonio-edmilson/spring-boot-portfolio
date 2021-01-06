package br.com.andrade.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrade.domain.resource.DigitoUnicoResource;
import br.com.andrade.domain.resource.DigitoUnicoUsuarioResource;
import br.com.andrade.repository.DigitoUnicoService;

@RestController
@RequestMapping("/digitosUnicos")
public class DigitoUnicoController extends BaseController{
	
	private static final long serialVersionUID = 5206387474117488093L;

	private Map<String, Integer> cacheDigitos;

	private DigitoUnicoService digitoUnicoService;

	public DigitoUnicoController(DigitoUnicoService digitoUnicoService) {
		this.digitoUnicoService = digitoUnicoService;
		cacheDigitos = new HashMap<>();
	}

	@PostMapping(path = "/calcular")
	public ResponseEntity<DigitoUnicoUsuarioResource> calcular(@RequestBody @Valid DigitoUnicoResource digitoUnicoResource) {
		String numeroCalcular = digitoUnicoService.concatenarNumeroCalcular(digitoUnicoResource.getNumero(), digitoUnicoResource.getNumeroParaConcatenar());
		Integer digitoUnicoCalculado = getDigitoCache(numeroCalcular);
		if(digitoUnicoCalculado == null) {
			digitoUnicoCalculado = digitoUnicoService.calcular(numeroCalcular);
			adicionarDigitoCache(numeroCalcular, digitoUnicoCalculado);
		}
		if(digitoUnicoResource.getIdUsuario() !=null) {
			digitoUnicoResource = digitoUnicoService.salvar(digitoUnicoResource, digitoUnicoCalculado);
		}
		return ResponseEntity.ok().body(DigitoUnicoUsuarioResource.toResource(digitoUnicoResource.getId(), digitoUnicoCalculado, numeroCalcular, digitoUnicoResource.getIdUsuario()));
	}
	
	@GetMapping(path = {"/{id}"}, name = "/digitosUnicosUsuario")
	public ResponseEntity<List<DigitoUnicoUsuarioResource>> pesquisarDigitosUnicosPorUsuario(Long id) {
		List<DigitoUnicoUsuarioResource> resultado =  digitoUnicoService.pesquisarDigitosUnicosPorUsuario(id);
		if(!resultado.isEmpty()) {
			return ResponseEntity.ok().body(resultado);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private Integer getDigitoCache(String numero) {
		return cacheDigitos.get(numero);
	}
	
	private void adicionarDigitoCache(String numero, Integer digitoUnico) {
		if(cacheDigitos.size() < 10) {
			cacheDigitos.put(numero, digitoUnico);
		}else {
			removerCacheMaisAntigo();
			cacheDigitos.put(numero, digitoUnico);
		}
	}

	private void removerCacheMaisAntigo() {
		Entry<String, Integer> cache = cacheDigitos.entrySet().iterator().next();
		cacheDigitos.remove(cache.getKey());
	}


}
