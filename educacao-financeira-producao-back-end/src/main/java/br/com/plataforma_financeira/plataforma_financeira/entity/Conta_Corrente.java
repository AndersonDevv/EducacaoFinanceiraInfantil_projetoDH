package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Conta_Corrente {
	
	@Id
	//@GeneratedValue //Esse generate mesmo sem estratégia estava estava gerando chaves sequênciais
	private Integer cc_num_conta;
	
	@Column(columnDefinition = "integer", nullable = false)
	private Integer cc_num_agencia;
	
	@Column(columnDefinition = "double default 0", nullable = false)
	private double cc_saldo;
	
	@Column(columnDefinition = "char default 0",  nullable = false)
	private char cc_ativa;
	
	@Column(columnDefinition = "double default 1", nullable = false)
	private double cc_tarifa;
	
	@JsonIgnore
	@OneToMany(mappedBy = "conta_corrente", cascade = CascadeType.REMOVE)
	private List<Extrato_Conta_Corrente> extrato_conta_corrente;
	
	/*
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, optional = true) 	
	@JoinColumn(name = "cartao_car_numero", referencedColumnName = "car_numero", nullable=false)	
    private Cartao cartao;	
    */
	

	/*
	//********************************
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dep_num_seq", nullable = false)
	private Dependente dependente;

	@JsonManagedReference
	@OneToMany(mappedBy = "conta_corrente", cascade = CascadeType.REMOVE)
	private List<Extrato_Conta_Corrente> extrato_conta_corrente;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true) 	
	@JoinColumn(name = "dependente_dep_num_seq", referencedColumnName = "dep_num_seq", nullable=false)	
    private Dependente dependente;
	
	
	//@JsonBackReference
	//@OneToOne(mappedBy = "conta_corrente")
	//private Dependente dependentes;
	
	//private Integer dependente_dep_num_sep;
	//private Integer dependente_usuario_usu_cpf;

	
	*/

}
