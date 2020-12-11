package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.RendaFixa;
import br.com.plataforma_financeira.plataforma_financeira.entity.Renda_Variavel;
import br.com.plataforma_financeira.plataforma_financeira.repository.RendaVariavelRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class RendaVariavelService {
	
	@Autowired
	private RendaVariavelRepository rendaVariavelRepository;
	
	
	public List<Renda_Variavel> findAll(){		
		List<Renda_Variavel> rendavariavel = rendaVariavelRepository.findAll();
		return rendavariavel;
		
	}
	
	
	public Renda_Variavel buscarRendaVariavelId(Integer rv_codigo) {		
		Optional<Renda_Variavel> rendavariavel = rendaVariavelRepository.findById(rv_codigo);
		return rendavariavel.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + rv_codigo + ", Tipo " + RendaFixa.class.getName()));
		
	}


	public Renda_Variavel insert(Renda_Variavel rendavariavel) {		
		return rendaVariavelRepository.save(rendavariavel);
	}


	public Renda_Variavel update(Renda_Variavel rendavariavel) {		
		buscarRendaVariavelId(rendavariavel.getRv_codigo());
		return rendaVariavelRepository.save(rendavariavel);
	}



	public void deleteById(Integer rv_codigo) {
		rendaVariavelRepository.deleteById(rv_codigo);
		
	}
	
	

}
