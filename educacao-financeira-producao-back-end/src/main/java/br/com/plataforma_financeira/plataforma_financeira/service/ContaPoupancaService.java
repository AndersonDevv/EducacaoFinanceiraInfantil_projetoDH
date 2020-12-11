package br.com.plataforma_financeira.plataforma_financeira.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Corrente;
import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Poupanca;
import br.com.plataforma_financeira.plataforma_financeira.repository.ContaPoupancaRepository;

@Service
public class ContaPoupancaService {

	@Autowired
	private ContaPoupancaRepository contaPoupancaRepository;
	
	public List<Conta_Poupanca> findAll(){		
		List<Conta_Poupanca> conta_poupanca = contaPoupancaRepository.findAll();
		return conta_poupanca;
		
	}
	
	public Conta_Poupanca insert(Conta_Poupanca conta_poupanca) {
		 conta_poupanca.setCp_num_conta(null);
		 return contaPoupancaRepository.save(conta_poupanca);	
	}
	
	public List<Conta_Poupanca> buscarContaPoupancaData(Date cp_data_aniversario) {
		
		 return contaPoupancaRepository.findByContaPoupancaDate(cp_data_aniversario);	
	}
	
	public List<Conta_Poupanca> buscarContaPoupancaDataInterval(Date cp_data_aniversario_inicio, Date cp_data_aniversario_fim) {
		
		 return contaPoupancaRepository.findByContaPoupancaInterval(cp_data_aniversario_inicio, cp_data_aniversario_fim);
	}

}
