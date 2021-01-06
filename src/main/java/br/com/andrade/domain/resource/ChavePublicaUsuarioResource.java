package br.com.andrade.domain.resource;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChavePublicaUsuarioResource {
	
	@NotBlank(message = "Usuário é obrigatório")
	private Long idUsuario;
	
	@NotBlank(message = "Chave pública é obrigatório")
	private String chavePublica;

}
