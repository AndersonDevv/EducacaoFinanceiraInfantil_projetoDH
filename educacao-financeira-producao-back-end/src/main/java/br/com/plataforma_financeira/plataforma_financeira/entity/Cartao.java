package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data//Gera os getters e setters
@Entity
public class Cartao {
	
	@Id		
	private Integer car_numero;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "date", nullable = false)
	private Date car_data_vencimento;
	
	@Column(columnDefinition = "double default 10.00")
	private double car_limite;	
	
	@Column(columnDefinition = "char default 0",  nullable = false )
	private char car_ativo;	
	
	/*
	@JsonBackReference
	@OneToOne(mappedBy = "cartao")
	private Conta_Corrente conta_corrente;
	
	*/
	

}



