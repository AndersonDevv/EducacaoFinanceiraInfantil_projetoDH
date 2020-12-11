package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Corrente;
import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Poupanca;

public interface ContaCorrenteRepository extends JpaRepository<Conta_Corrente, Integer>{
	
	

}
