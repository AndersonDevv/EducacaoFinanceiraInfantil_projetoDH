package br.com.plataforma_financeira.plataforma_financeira.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {	
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "integer", nullable = false)
	private Integer cat_codigo;
	
	@Column(nullable = false, unique = true)
	private String cat_nome;	
	
	
	//Criado para a conversão de uma CategoriaDTO para Categoria
	public Categoria(String nome) {
		this.cat_nome = nome;
	}
	
	
	

}