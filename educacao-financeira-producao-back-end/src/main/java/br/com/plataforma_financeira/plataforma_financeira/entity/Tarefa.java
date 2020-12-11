package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer tar_codigo;
	
	@Column(nullable = false)
	private String tar_titulo;
	
	@Column(nullable = false)
	private String tar_descricao;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "Date", nullable = false)
	private Date tar_data_inicio;
	
	@Column(nullable = false)
	private String tar_status;
	
	@Column(nullable = false)
	private String tar_objetivo;
	
	@Column(columnDefinition = "integer", nullable = false)
	private Integer tar_pontos;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "Date", nullable = false)
	private Date tar_data_fim;
	
	
	//@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cat_codigo", nullable=true)
	private Categoria categoria;
	

	@ManyToMany
	@JoinTable(name= "tarefa_dia", joinColumns = @JoinColumn(name = "tar_codigo"), inverseJoinColumns = @JoinColumn(name = "dia_codigo"))
	private List<Dias_Semana> dias_semana = new ArrayList<>();
	
	/*
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "dep_codigo", referencedColumnName = "dep_codigo",  nullable=false)
	private Dependente dependente;
	*/

	/*
	@ManyToMany
	@JoinTable(name= "categoria_tarefa", joinColumns = @JoinColumn(name = "tar_codigo"), inverseJoinColumns = @JoinColumn(name = "cat_codigo" ))
	private List<Dias_Semana> dias_semana = new ArrayList<>();
	*/
	//@ManyToMany(mappedBy="tarefas")

	
	
}
