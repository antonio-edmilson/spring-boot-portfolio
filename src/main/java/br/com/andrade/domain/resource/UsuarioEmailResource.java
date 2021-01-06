package br.com.andrade.domain.resource;

import java.io.Serializable;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEmailResource implements Serializable{
	
	private static final long serialVersionUID = 2595240061132989161L;
	
	private Long id;
	
	@Email(message = "E-mail não é valido.")
	private String email;
	
	private String nome;

}
