package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.plataforma_financeira.plataforma_financeira.entity.Dependente;




public interface DependenteRepository extends JpaRepository<Dependente, Integer>{

	Optional<Dependente> findById(Integer id);
	
	@Query(value = "SELECT * FROM dependente WHERE dependente.usuario_usu_id = :usuario_usu_id" , nativeQuery = true)
	List<Dependente> findByDependentePorUsuarioId(Integer usuario_usu_id);

}
