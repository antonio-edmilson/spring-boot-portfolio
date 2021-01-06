package br.com.andrade.domain.resource;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitoUnicoUsuarioResource implements Serializable {

	private static final long serialVersionUID = -2411173088906112558L;

	private Long id;
	private Integer digitoUnico;
	private String numeroCalculado;
	private Long idUsuario;

	public static DigitoUnicoUsuarioResource toResource(Long id, Integer digitoUnico, String numeroCalculado, Long idUsuario) {
		return new DigitoUnicoUsuarioResource(id, digitoUnico, numeroCalculado, idUsuario);
	}

}
