package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Extrato_Conta_Corrente {
	
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer ext_id;
		
		@JsonFormat(pattern="dd-MM-yyyy")
		@Column(columnDefinition = "date", nullable = false)
		private Date ext_data;
		
		@Column(nullable = false, length = 1)
		private String ext_tipo;
		
		@Column(nullable = false, length = 50)
		private String ext_descricao;
		
		@Column(nullable = false)
		private float ext_valor;
		
		@Transient
		private Integer contaCorrente_cc_num_conta;
		
		@JsonIgnore
		@ManyToOne	
		@JoinColumn(name="conta_corrente_cc_num_conta", referencedColumnName = "cc_num_conta", nullable=false)
		private Conta_Corrente conta_corrente;

		
		

}
