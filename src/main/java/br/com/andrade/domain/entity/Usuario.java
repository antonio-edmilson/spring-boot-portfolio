package br.com.andrade.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "tb_usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 5962481213707626859L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 500)
	private String nome;
	
	@Column(length = 500)
	private String email;
	
	private Integer idade;
	
	@Column(length = 500)
	private String chavePublica;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<DigitoUnico> digitosUnicos;
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(String nome, String email, Integer idade) {
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
	
	public Usuario(String nome, String email, Set<DigitoUnico> digitosUnicos) {
		this.nome = nome;
		this.email = email;
		this.digitosUnicos = digitosUnicos;
	}

}
