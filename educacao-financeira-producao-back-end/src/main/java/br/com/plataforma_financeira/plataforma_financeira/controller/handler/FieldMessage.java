package br.com.plataforma_financeira.plataforma_financeira.controller.handler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String message;
	

	
}