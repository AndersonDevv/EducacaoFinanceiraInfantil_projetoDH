package br.com.plataforma_financeira.plataforma_financeira.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Dias_Semana {	
	
	@Id //chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer dia_codigo;	
	
	@Column(nullable = false)
	private String dia_nome;	

}
