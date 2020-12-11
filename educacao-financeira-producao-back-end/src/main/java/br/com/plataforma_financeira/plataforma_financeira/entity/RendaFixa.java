package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class RendaFixa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rf_codigo;
	
	@Column(columnDefinition = "double default 0", nullable = false)
	private double rf_valor_investido;
	
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "date")
	private Date rf_data_investimento;
	
	@Column(columnDefinition = "double default 1", nullable = false)
    private double rf_iof;
    
	@Column(columnDefinition = "double default 0", nullable = false)
    private double rf_rendimento;
    
   
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(columnDefinition = "date")
    private Date rf_data_resgate;

	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dep_num_seq", nullable = false)
	private Dependente dependente;
}
