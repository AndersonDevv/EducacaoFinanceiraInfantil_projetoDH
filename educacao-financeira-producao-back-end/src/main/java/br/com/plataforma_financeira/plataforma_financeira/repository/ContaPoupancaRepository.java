package br.com.plataforma_financeira.plataforma_financeira.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Poupanca;

public interface ContaPoupancaRepository extends JpaRepository<Conta_Poupanca, Integer>{
	
	    //@Query(value = "select s from Conta_corrente s where s.cp_data_aniversario = :cp_data_aniversario", nativeQuery = true)
		//List<Conta_Poupanca> findBySName(Date cp_data_aniversario);
	    
	    @Query(value = "SELECT * FROM conta_poupanca WHERE cp_data_aniversario = :cp_data_aniversario", nativeQuery = true)
		List<Conta_Poupanca> findByContaPoupancaDate(Date cp_data_aniversario);

		@Query(value = "select * from conta_poupanca where cp_data_aniversario  between :cp_data_aniversario_inicio and :cp_data_aniversario_fim", nativeQuery = true)
		List<Conta_Poupanca> findByContaPoupancaInterval(Date cp_data_aniversario_inicio, Date cp_data_aniversario_fim);

}
