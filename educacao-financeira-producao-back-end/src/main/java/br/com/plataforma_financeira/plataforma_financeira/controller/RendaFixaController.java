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

import br.com.plataforma_financeira.plataforma_financeira.entity.RendaFixa;
import br.com.plataforma_financeira.plataforma_financeira.service.RendaFixaService;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class RendaFixaController {
	
	
	@Autowired
	private RendaFixaService rendaFixaService;		
	
	
	@GetMapping("/rendafixa")	
	public List<RendaFixa> listarRendaFixa() {		
		List<RendaFixa> rendafixa = rendaFixaService.findAll();
		return rendafixa;		
	}
	
		
	
	@GetMapping("/rendafixa/{rf_codigo}")	
	public ResponseEntity<?> buscarRendaFixaId(@PathVariable Integer rf_codigo) {	
		RendaFixa rendafixa = rendaFixaService.buscarRendaFixaId(rf_codigo);
		return ResponseEntity.ok().body(rendafixa);
		
	}
	
	
	
	@PostMapping("/rendafixa")	
	//ResponsyEntity retorna uma resposta http. E o Void indica que é sem corpo
    public ResponseEntity<Void> inserirRendaFixa(@RequestBody RendaFixa rendafixa) {	
		
		rendafixa = rendaFixaService.insert(rendafixa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(rendafixa.getRf_codigo()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	

	
	@PutMapping("/rendafixa/{rf_codigo}")	
    public ResponseEntity<Void> atualizaRendaFixa(@RequestBody RendaFixa rendafixa, @PathVariable Integer rf_codigo) {				
		rendafixa.setRf_codigo(rf_codigo);;
		rendafixa = rendaFixaService.update(rendafixa);				
		return ResponseEntity.noContent().build();
		
	}
	

	
	
	@DeleteMapping("/rendafixa/{rf_codigo}")	
	public ResponseEntity<Void> deletarRendaFixa(@PathVariable Integer rf_codigo) {	
		rendaFixaService.deleteById(rf_codigo);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	

}
