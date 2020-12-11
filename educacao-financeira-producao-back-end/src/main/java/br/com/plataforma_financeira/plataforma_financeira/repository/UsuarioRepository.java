package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//Optional<Usuario> findByUsuNome(String usuNome);	
	
	//Optional<Usuario> findByEmail(String email);
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.email = :email")
	Optional<Usuario> findByEmail(String email);

	//Boolean existsByUsuNome(String usuNome);
	Boolean existsByEmail(String email);
}
