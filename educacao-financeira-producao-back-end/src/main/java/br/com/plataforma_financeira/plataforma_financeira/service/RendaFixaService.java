package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.RendaFixa;
import br.com.plataforma_financeira.plataforma_financeira.repository.RendaFixaRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class RendaFixaService {
	
	@Autowired
	private RendaFixaRepository rendaFixaRepository;
	
	
	public List<RendaFixa> findAll(){		
		List<RendaFixa> conta_corrente = rendaFixaRepository.findAll();
		return conta_corrente;
		
	}
	
	
	public RendaFixa buscarRendaFixaId(Integer rf_codigo) {		
		Optional<RendaFixa> rendafixa = rendaFixaRepository.findById(rf_codigo);
		return rendafixa.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + rf_codigo + ", Tipo " + RendaFixa.class.getName()));
		
	}


	public RendaFixa insert(RendaFixa rendafixa) {		
		return rendaFixaRepository.save(rendafixa);
	}


	public RendaFixa update(RendaFixa rendafixa) {		
		buscarRendaFixaId(rendafixa.getRf_codigo());
		return rendaFixaRepository.save(rendafixa);
	}



	public void deleteById(Integer rf_codigo) {
		rendaFixaRepository.deleteById(rf_codigo);
		
	}
	
	

}
