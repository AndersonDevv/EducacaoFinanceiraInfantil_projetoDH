package br.com.plataforma_financeira.plataforma_financeira.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String usu_senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsu_senha() {
		return usu_senha;
	}

	public void setUso_senha(String usu_senha) {
		this.usu_senha = usu_senha;
	}
}