package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Corrente;
import br.com.plataforma_financeira.plataforma_financeira.entity.Conta_Poupanca;
import br.com.plataforma_financeira.plataforma_financeira.service.ContaPoupancaService;

@RestController//Indica uma classe controladora do spring com suporte a api rest 
@RequestMapping("api/v1")//Indica o endpoit através do qual iremos acessar o nosso controller
public class ContaPoupancaController {
	
	
	@Autowired
	private ContaPoupancaService contaPoupancaService;	
	
	@GetMapping("/conta_poupanca")	
	public List<Conta_Poupanca> listarContas() {		
		List<Conta_Poupanca> conta_poupanca = contaPoupancaService.findAll();
		return conta_poupanca;		
	}
	
	
	@GetMapping("/conta_poupanca/data/")	
	public List<Conta_Poupanca> findContaPoupancaData(@RequestParam("cp_data_aniversario")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date cp_data_aniversario) {		
			List<Conta_Poupanca> conta_poupanca = contaPoupancaService.buscarContaPoupancaData(cp_data_aniversario);
			return conta_poupanca;			
	}
	
	
	@GetMapping("/conta_poupanca/data_intervalo/")	
	public List<Conta_Poupanca> findContaPoupancaDataInterval(@RequestParam("cp_data_aniversario_inicio")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date cp_data_aniversario_inicio, 
			@RequestParam("cp_data_aniversario_fim")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date cp_data_aniversario_fim) {		
			List<Conta_Poupanca> conta_poupanca = contaPoupancaService.buscarContaPoupancaDataInterval(cp_data_aniversario_inicio, cp_data_aniversario_fim);
			return conta_poupanca;			
	}
	
	
	
	@PostMapping("/conta_poupanca")	
    public ResponseEntity<Void> insert(@RequestBody Conta_Poupanca contaPoupanca) {				
		contaPoupanca = contaPoupancaService.insert(contaPoupanca);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contaPoupanca.getCp_num_conta()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	

}
