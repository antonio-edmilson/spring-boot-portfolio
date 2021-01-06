package br.com.andrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andrade.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
