package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Corrente;
import br.com.plataforma_financeira.plataforma_financeira.repository.ContaCorrenteRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class ContaCorrenteService {
	
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	
	public Conta_Corrente insert(Conta_Corrente conta_corrente) {		 
		 return contaCorrenteRepository.save(conta_corrente);
	}
	
	
	public List<Conta_Corrente> findAll(){		
		List<Conta_Corrente> conta_corrente = contaCorrenteRepository.findAll();
		return conta_corrente;
		
	}
	
	
	public Conta_Corrente find(Integer id) {		
		Optional<Conta_Corrente> Conta_corrente = contaCorrenteRepository.findById(id);
		return Conta_corrente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo " + Conta_Corrente.class.getName()));
		
	}


	public Conta_Corrente update(Conta_Corrente contaCorrente) {
		find(contaCorrente.getCc_num_conta());
		return contaCorrenteRepository.save(contaCorrente);
	}


	public void deleteById(Integer cc_num_conta) {
		contaCorrenteRepository.deleteById(cc_num_conta);
		
	}
	
	
	

}
