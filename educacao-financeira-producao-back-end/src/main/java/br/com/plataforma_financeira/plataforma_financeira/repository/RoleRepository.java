package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.plataforma_financeira.plataforma_financeira.entity.ERole;
import br.com.plataforma_financeira.plataforma_financeira.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
