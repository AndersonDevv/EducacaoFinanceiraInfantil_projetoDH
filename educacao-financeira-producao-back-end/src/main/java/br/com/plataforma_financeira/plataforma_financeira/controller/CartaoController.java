package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.entity.Cartao;
import br.com.plataforma_financeira.plataforma_financeira.service.CartaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController//Indica uma classe controladora do spring com suporte a api rest 
@RequestMapping("api/v1")//Indica o endpoit através do qual iremos acessar o nosso controller
public class CartaoController {
	
	@Autowired
	private CartaoService cartaoService;	
	
	//---------------------Buscar Cartões-------------------------------------------------------------------------
	@ApiOperation(value = "Retorna todos os cartões")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Método apenas para testar o recurso de cartões")})	
	@GetMapping("/cartao")	
    public List<Cartao> buscarCartoes() {			
		List<Cartao> cartao = cartaoService.findAll();
		return cartao;		
	}	
	
	
	//---------------------Buscar Cartões Ativos/Inativos-------------------------------------------------------------------------
		
		@GetMapping("/cartao/status/{tipo}")	
	    public List<Cartao> buscarCartoesAtivosInativos(@PathVariable char tipo) {			
			List<Cartao> cartao = cartaoService.findAllAtivos(tipo);
			return cartao;		
		}	
	
	
	//---------------------Buscar Cartão Por Id-------------------------------------------------------------------------
	@GetMapping("/cartao/{car_numero}")	
	public ResponseEntity<?> buscarContaoId(@PathVariable Integer car_numero) {	
		Cartao cartao = cartaoService.find(car_numero);
		return ResponseEntity.ok().body(cartao);
		
	}
	
	
	//---------------------Insere um Cartão-------------------------------------------------------------------------
	@PostMapping("/cartao")	
	//ResponsyEntity retorna uma resposta http. E o Void indica que é sem corpo
    public ResponseEntity<Void> inserirCartao(@RequestBody Cartao cartao) {		
		cartao = cartaoService.insert(cartao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cartao.getCar_numero()).toUri();		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	//---------------------Atualiza um Cartão-------------------------------------------------------------------------
	@PutMapping("/cartao/{car_numero}")	
    public ResponseEntity<Void> atualizarCartao(@RequestBody Cartao cartao, @PathVariable(value="car_numero") Integer car_numero) {				
		cartao.setCar_numero(car_numero);
		cartao = cartaoService.update(cartao);				
		return ResponseEntity.noContent().build();
		
	}
	
	
}
