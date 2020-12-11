package br.com.plataforma_financeira.plataforma_financeira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.plataforma_financeira.plataforma_financeira.entity.Dias_Semana;
import br.com.plataforma_financeira.plataforma_financeira.entity.Tarefa;
import br.com.plataforma_financeira.plataforma_financeira.repository.DiasSemanaRepository;
import br.com.plataforma_financeira.plataforma_financeira.repository.TarefaRepository;

@RestController
@RequestMapping("api/v1")
public class DiasSemanaController {

	@Autowired
	private DiasSemanaRepository dias_semanaRepository;
	
	@GetMapping("/dias_semana")
	public Iterable<Dias_Semana> findAll(){
		return dias_semanaRepository.findAll();
	}
	
	@PostMapping("/dias_semana")
	public Dias_Semana addDias_Semana(@RequestBody Dias_Semana dias_semana) {
		dias_semanaRepository.save(dias_semana);
		return dias_semana;
	}
}
