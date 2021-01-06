package br.com.andrade.domain.resource;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.andrade.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResource implements Serializable {

	private static final long serialVersionUID = -5109967781391237686L;

	private Long id;
	
	@NotBlank(message = "Nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "E-mail é obrigatório.")
	@Email(message = "E-mail não é valido.")
	private String email;
	
	private Integer idade;

	public static Usuario toDomain(UsuarioResource resource) {
		return new Usuario(resource.getNome(), resource.getEmail(), resource.getIdade());
	}

	public static UsuarioResource toResource(Usuario usuario) {
		return new UsuarioResource(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getIdade());
	}

}
