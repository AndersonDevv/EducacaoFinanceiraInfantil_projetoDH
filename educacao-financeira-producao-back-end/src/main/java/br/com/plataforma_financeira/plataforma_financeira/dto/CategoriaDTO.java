package br.com.plataforma_financeira.plataforma_financeira.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.plataforma_financeira.plataforma_financeira.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	//private Integer cat_codigo;
	
	@NotEmpty(message = "Preenchimetno obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 8 caracteres")
	private String cat_nome;	
	
	/*
	@NotEmpty(message = "Preenchimetno obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 8 caracteres")
	private Integer cat_codigo;	
*/

	public CategoriaDTO(Categoria categoria) {
		//this.cat_codigo = categoria.getCat_codigo();
		this.cat_nome = categoria.getCat_nome();
	}


	
	

}
