package br.com.plataforma_financeira.plataforma_financeira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma_financeira.plataforma_financeira.entity.Tarefa;
import br.com.plataforma_financeira.plataforma_financeira.repository.TarefaRepository;
import br.com.plataforma_financeira.plataforma_financeira.service.TarefaService;

@RestController
@RequestMapping("api/v1")
public class TarefaController {
	
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaService tarefaService;
	
	@GetMapping("/tarefa")
	public Iterable<Tarefa> findAll(){
		return tarefaRepository.findAll();
	}
	
	@PostMapping("/tarefa")
	public Tarefa addTarefa(@RequestBody Tarefa tarefa) {
		tarefaRepository.save(tarefa);
		return tarefa;
	}
	
	@PutMapping("/tarefa/{tar_codigo}")	
    public ResponseEntity<Void> updateConta(@RequestBody Tarefa tarefa, @PathVariable Integer tar_codigo) {				
		tarefa.setTar_codigo(tar_codigo);
		tarefa = tarefaService.update(tarefa);				
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/tarefa/{tar_codigo}")	
	public ResponseEntity<Void> deletarConta(@PathVariable Integer tar_codigo) {	
		tarefaService.deleteById(tar_codigo);
		return ResponseEntity.noContent().build();
		
	}

}
