package br.com.andrade.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tb_digito_unico")
public class DigitoUnico implements Serializable {

	private static final long serialVersionUID = -2467908446764360485L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer digitoUnico;

	private String numeroCalculado;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}
