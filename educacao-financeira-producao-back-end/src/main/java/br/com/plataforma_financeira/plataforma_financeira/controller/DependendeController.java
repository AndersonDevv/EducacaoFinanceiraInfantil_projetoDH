package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.entity.Dependente;
import br.com.plataforma_financeira.plataforma_financeira.service.DependenteService;


@RestController
@RequestMapping("api/v1")
public class DependendeController {
	
	@Autowired
	private DependenteService dependenteService;
	
	@CrossOrigin
	@GetMapping("/dependente")
	public List<Dependente> getDependentes(){
		List<Dependente> dependentes = dependenteService.findAll();
		return dependentes; 
	}
	
	
	
	@CrossOrigin
	@GetMapping("/dependente/{dep_num_seq}")
	public Dependente getById(@PathVariable Integer dep_num_seq) {
		Dependente dependente  = dependenteService.findById(dep_num_seq);
		return dependente;
	}
	
	@CrossOrigin
	@GetMapping("/dependente/buscardependentesporusuarioid/{usuario_usu_id}")
	public List<Dependente> buscarDependentesPorUsuarioId(@PathVariable Integer usuario_usu_id) {
		List<Dependente> dependente  = dependenteService.buscarDependentesPorUsuarioId(usuario_usu_id);
		return dependente;
	}
	
	@CrossOrigin
	@PostMapping("/dependente")	
    public ResponseEntity<Void> insert(@RequestBody Dependente dependente) {		
		dependente = dependenteService.insert(dependente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dependente.getDep_num_seq()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@CrossOrigin
	@DeleteMapping("/dependente/{dep_codigo}")
	public ResponseEntity<Void> delete(@PathVariable Integer dep_codigo){
		dependenteService.delete(dep_codigo);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/dependente/{dep_codigo}")	
    public ResponseEntity<Void> update(@RequestBody Dependente dependente, @PathVariable Integer dep_codigo) {				
		dependente.setDep_codigo(dep_codigo);
		dependente = dependenteService.updateDependente(dependente);				
		return ResponseEntity.noContent().build();
		
	}

}
