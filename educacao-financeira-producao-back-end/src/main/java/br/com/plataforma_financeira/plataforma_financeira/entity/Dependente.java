package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Dependente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dep_codigo; 
	
	@Column(nullable = false)
	private Integer dep_num_seq;
	
	@Column(nullable = false)
	private String dep_nome;
	
	@Column(nullable = false)
	private String dep_genero;
	
	@JsonFormat(pattern="dd-MM-yyyy")	
	@Column(columnDefinition = "DATE", nullable = false)	
	private Date dep_data_nascimento;
	
	@Column(nullable = false, unique = true, length = 50)
	private String dep_usuario;
	
	@Column(nullable = false, length = 512)
	private String dep_senha;
	
	@Column(nullable = false, length = 150)
	private String dep_url_foto;	
	
	@Column(nullable = false)
	private Integer usuario_usu_id;		
	
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name="dep_codigo")
	 private List<Tarefa> tarefas;
	 
	 
	
	/*
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_usu_id", referencedColumnName = "usu_id", nullable=false)
	private Usuario usuario;
	*/
/*
	@OneToMany(mappedBy = "dependente", cascade = CascadeType.ALL)	
	private List<Tarefa> tarefas;
	
	*/
	
	
	 
	
	/*
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="dep_codigo", referencedColumnName = "dep_codigo", nullable=true)
	private List<Tarefa> tarefas;
	*/

		
	/*
	@JsonBackReference
	@OneToOne(mappedBy = "dependente")
	private Renda_Variavel renda_variavel;
	*/
	
	/*
	@JsonBackReference
	@OneToOne(mappedBy = "dependente")
	private RendaFixa rendafixa;
	*/

	/*
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cp_num_conta", referencedColumnName = "cp_num_conta", nullable=true)
	private Conta_Poupanca conta_poucanca;
	
	*/

}
