package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Dependente;
import br.com.plataforma_financeira.plataforma_financeira.repository.DependenteRepository;
import br.com.plataforma_financeira.plataforma_financeira.repository.TarefaRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;


@Service
public class DependenteService {
	
	@Autowired
	private DependenteRepository dependenteRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	public List<Dependente> findAll() {		
		return dependenteRepository.findAll();		
	}
	
	
	
	public Dependente findById(Integer id) {		
		Optional<Dependente> dependente = dependenteRepository.findById(id);
		return dependente.orElseThrow(() -> new ObjectNotFoundException("Dependente não encontrado! " + id + ", Tipo " + Dependente.class.getName()));
		
	}
	
	public Dependente insert(Dependente dependente) {			
		
		Dependente dep = dependenteRepository.save(dependente);		
		//tarefaRepository.saveAll(dependente.getTarefas());		
		return dep;
	}
	
	public void delete(Integer id) {
		dependenteRepository.deleteById(id);
	}
	
	public Dependente updateDependente(Dependente dependente) {
		find(dependente.getDep_codigo());		
		return dependenteRepository.save(dependente);
	}

	public Dependente find(Integer id) {
		Optional<Dependente> Dependente = dependenteRepository.findById(id);
		return Dependente.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado! "));
		
	}


	public List<Dependente> buscarDependentesPorUsuarioId(Integer usuario_usu_id) {
		List<Dependente> dependente = dependenteRepository.findByDependentePorUsuarioId(usuario_usu_id);
		return dependente;
	}
}
