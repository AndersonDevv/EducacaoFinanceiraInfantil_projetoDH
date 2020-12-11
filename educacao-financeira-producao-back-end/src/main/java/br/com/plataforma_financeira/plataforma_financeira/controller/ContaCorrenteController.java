package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Corrente;
import br.com.plataforma_financeira.plataforma_financeira.service.ContaCorrenteService;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class ContaCorrenteController {
	
	@Autowired
	private ContaCorrenteService contaCorrenteService;	
	
	@GetMapping("/conta_corrente")	
	public List<Conta_Corrente> listarContas() {		
		List<Conta_Corrente> conta_corrente = contaCorrenteService.findAll();
		return conta_corrente;		
	}
	
	
	@PostMapping("/conta_corrente")	
	//ResponsyEntity retorna uma resposta http. E o Void indica que é sem corpo
    public ResponseEntity<Void> insert(@RequestBody Conta_Corrente contaCorrente) {	
		
		contaCorrente = contaCorrenteService.insert(contaCorrente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contaCorrente.getCc_num_conta()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	@PutMapping("/conta_corrente/{cc_num_conta}")	
    public ResponseEntity<Void> updateConta(@RequestBody Conta_Corrente contaCorrente, @PathVariable(value="cc_num_conta") Integer cc_num_conta) {				
		contaCorrente.setCc_num_conta(cc_num_conta);
		contaCorrente = contaCorrenteService.update(contaCorrente);				
		return ResponseEntity.noContent().build();
		
	}

	
	
	@GetMapping("/conta_corrente/{cc_num_conta}")	
	public ResponseEntity<?> buscarContaCorrenteID(@PathVariable Integer cc_num_conta) {	
		Conta_Corrente conta_corrente = contaCorrenteService.find(cc_num_conta);
		return ResponseEntity.ok().body(conta_corrente);
		
	}
	
	@DeleteMapping("/conta_corrente/{cc_num_conta}")	
	public ResponseEntity<Void> deletarConta(@PathVariable Integer cc_num_conta) {	
		contaCorrenteService.deleteById(cc_num_conta);
		return ResponseEntity.noContent().build();
		
	}
	
	
	
	
	
	

}
