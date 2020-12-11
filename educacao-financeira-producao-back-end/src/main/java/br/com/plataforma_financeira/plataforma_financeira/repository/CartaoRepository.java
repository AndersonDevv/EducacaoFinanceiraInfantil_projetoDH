package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.plataforma_financeira.plataforma_financeira.entity.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer>{

	@Query("SELECT cartao FROM Cartao cartao WHERE cartao.car_ativo = :tipo")
	List<Cartao> listaCartoes(char tipo);

}
