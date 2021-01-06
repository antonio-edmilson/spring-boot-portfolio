package br.com.andrade.domain.resource;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.andrade.domain.entity.DigitoUnico;
import br.com.andrade.domain.entity.Usuario;
import br.com.andrade.validator.DigitoUnicoConstraint;
import br.com.andrade.validator.NumeroConcatenarConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitoUnicoResource implements Serializable {

	private static final long serialVersionUID = -5109967781391237686L;

	private Long id;

	@NotBlank(message = "Número é obrigatório.")
	@DigitoUnicoConstraint
	private String numero;

	@NumeroConcatenarConstraint
	private Integer numeroParaConcatenar = 1;
	
	private Long idUsuario;


	public DigitoUnicoResource(Long id, String numero, Long idUsuario) {
		this.id = id;
		this.numero = numero;
		this.idUsuario = idUsuario;
	}

	public static DigitoUnico toDomain(DigitoUnicoResource resource, Integer digitoUnico, Usuario usuario) {
		return new DigitoUnico(resource.getId(), digitoUnico, resource.getNumero(), usuario);
	}

	public static DigitoUnicoResource toResource(DigitoUnico digitoUnico) {
		Long idUsuario = digitoUnico.getUsuario() != null ? digitoUnico.getUsuario().getId() : null;
		return new DigitoUnicoResource(digitoUnico.getId(), digitoUnico.getNumeroCalculado(), idUsuario);
	}
}
