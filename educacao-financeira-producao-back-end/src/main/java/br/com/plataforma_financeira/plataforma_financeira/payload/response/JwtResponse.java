package br.com.plataforma_financeira.plataforma_financeira.payload.response;

import java.util.List;

import lombok.Data;


public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String usu_nome;
	private String email;
	private List<String> roles;
	
	

	public JwtResponse(String accessToken, Long id, String usu_nome, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.usu_nome = usu_nome;
		this.email = email;		
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getUsu_nome() {
		return usu_nome;
	}

	public void setUsu_nome(String usu_nome) {
		this.usu_nome = usu_nome;
	}

	public List<String> getRoles() {
		return roles;
	}
}
