package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Conta_Poupanca {
	
	
	@Id	
	private Integer cp_num_conta;	
	
	@Column(nullable = false)
	private Integer cp_num_agencia;
	
	@Column(columnDefinition = "double default 0", nullable = false)
	private double  cp_saldo;
	
	@Column(columnDefinition = "char default 0",  nullable = false)
	private char cp_ativa;
	
	@Column(columnDefinition = "double default 0", nullable = false)
	private double cp_rendimento;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	//@Column(columnDefinition = "dateTime", nullable = false)
	@Column(columnDefinition = "DATETIME", nullable = false)	
	private Date cp_data_aniversario;
	
	/*
	@OneToOne(cascade = CascadeType.ALL, optional = true) 	
	@JoinColumn(name = "dependente_dep_num_seq", referencedColumnName = "dep_num_seq", nullable=false)	
    private Dependente dependente;
    */

}
