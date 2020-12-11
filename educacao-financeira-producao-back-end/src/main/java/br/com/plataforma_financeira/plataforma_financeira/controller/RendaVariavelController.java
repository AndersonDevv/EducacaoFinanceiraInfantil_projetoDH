package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.entity.Renda_Variavel;
import br.com.plataforma_financeira.plataforma_financeira.service.RendaVariavelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController//Indica uma classe controladora do spring com suporte a api rest 
@RequestMapping("api/v1")//Indica o endpoit através do qual iremos acessar o nosso controller
public class RendaVariavelController {
	
	@Autowired
    private RendaVariavelService rendaVariavelService;	
	
	
	//---------------------Lista Renda Variável-------------------------------------------------------------------------
	@ApiOperation(value = "Retorna uma lista de investimentos - Renda Variável.")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Dados retornados com sucesso.")})	
	@GetMapping("/rendavariavel")	
	public List<Renda_Variavel> listarRendaVariavel() {		
		List<Renda_Variavel> rendavariavel = rendaVariavelService.findAll();
		return rendavariavel;		
	}
	
		
	
	@GetMapping("/rendavariavel/{rv_codigo}")	
	public ResponseEntity<?> buscarRendaFixaId(@PathVariable Integer rv_codigo) {	
		Renda_Variavel rendavariavel = rendaVariavelService.buscarRendaVariavelId(rv_codigo);
		return ResponseEntity.ok().body(rendavariavel);
		
	}
	
	
	
	@PostMapping("/rendavariavel")	
	//ResponsyEntity retorna uma resposta http. E o Void indica que é sem corpo
    public ResponseEntity<Void> inserirRendaVariavel(@RequestBody Renda_Variavel rendavariavel) {		
		rendavariavel = rendaVariavelService.insert(rendavariavel);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(rendavariavel.getRv_codigo()).toUri();		
		return ResponseEntity.created(uri).build();
		
	}
	
	

	
	@PutMapping("/rendavariavel/{rv_codigo}")	
    public ResponseEntity<Void> atualizaRendaVariavel(@RequestBody Renda_Variavel rendavariavel, @PathVariable Integer rv_codigo) {				
		rendavariavel.setRv_codigo(rv_codigo);
		rendavariavel = rendaVariavelService.update(rendavariavel);				
		return ResponseEntity.noContent().build();
		
	}
	

	
	
	@DeleteMapping("/rendavariavel/{rv_codigo}")	
	public ResponseEntity<Void> deletarRendaFixaVariavel(@PathVariable Integer rv_codigo) {	
		rendaVariavelService.deleteById(rv_codigo);
		return ResponseEntity.noContent().build();
		
	}
	
	
	

}
