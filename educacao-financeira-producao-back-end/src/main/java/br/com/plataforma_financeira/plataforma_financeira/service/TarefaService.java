package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Tarefa;
import br.com.plataforma_financeira.plataforma_financeira.repository.TarefaRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class TarefaService {

	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	
	public Tarefa find(Integer id) {		
		Optional<Tarefa> Tarefa = tarefaRepository.findById(id);
		return Tarefa.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo " + Tarefa.class.getName()));
		
	}


	public Tarefa update(Tarefa tarefa) {
		find(tarefa.getTar_codigo());
		return tarefaRepository.save(tarefa);
	}


	public void deleteById(Integer tar_codigo) {
		tarefaRepository.deleteById(tar_codigo);
	}
}
