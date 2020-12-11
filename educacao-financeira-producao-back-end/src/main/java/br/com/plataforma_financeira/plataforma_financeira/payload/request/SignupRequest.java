package br.com.plataforma_financeira.plataforma_financeira.payload.request;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import br.com.plataforma_financeira.plataforma_financeira.entity.Dependente;

 

public class SignupRequest {
	
	 
	 private Long usu_id;
	
    @NotBlank
    @Size(min = 3, max = 20)
    private String usu_nome;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 8)
    private String usu_senha;
    
    @NotBlank
    @Size(min = 11, max = 11)
    private String usu_cpf;  
    
    
    @NotBlank
    @Size(min = 8, max = 40)
    private String usu_cep;  
    
    @NotBlank
    @Size(min = 2, max = 20)
    private String usu_tipo_endereco; 
    
    @NotBlank
    @Size(min = 1, max = 6)
    private String usu_numero;  
    
    @NotBlank
    @Size(min = 4, max = 30)
    private String usu_nome_end; 
    
    public String getUsu_senha() {
		return usu_senha;
	}

	public void setUsu_senha(String usu_senha) {
		this.usu_senha = usu_senha;
	}

	@NotBlank
    @Size(min = 4, max = 20)
    private String usu_complemento;  
    
    @NotBlank
    @Size(min = 5, max = 40)
    private String usu_bairro;
    
    @NotBlank
    @Size(min = 4, max = 30)
    private String usu_cidade; 
    
    @NotBlank
    @Size(min = 2, max = 2)
    private String usu_uf;
	
    @NotBlank
    @Size(min = 11, max = 11)
	private String usu_tel_celular;
	
    @NotBlank
    @Size(min = 10, max = 10)
	private String usu_tel_fixo;
    
    @NotNull   
    private char usu_status;  
    
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Dependente> dependentes;	
	
      
    public Long getUsu_id() {
		return usu_id;
	}

	

	public void setUsu_id(Long usu_id) {
		this.usu_id = usu_id;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	public String getUsu_cpf() {
		return usu_cpf;
	}

	public void setUsu_cpf(String usu_cpf) {
		this.usu_cpf = usu_cpf;
	}

	public String getUsu_numero() {
		return usu_numero;
	}

	public void setUsu_numero(String usu_numero) {
		this.usu_numero = usu_numero;
	}

	public String getUsu_complemento() {
		return usu_complemento;
	}

	public void setUsu_complemento(String usu_complemento) {
		this.usu_complemento = usu_complemento;
	}

	public char getUsu_status() {
		return usu_status;
	}

	public void setUsu_status(char usu_status) {
		this.usu_status = usu_status;
	}

	public String getUsu_uf() {
		return usu_uf;
	}

	public void setUsu_uf(String usu_uf) {
		this.usu_uf = usu_uf;
	}

	public String getUsu_tel_celular() {
		return usu_tel_celular;
	}

	public void setUsu_tel_celular(String usu_tel_celular) {
		this.usu_tel_celular = usu_tel_celular;
	}

	public String getUsu_tel_fixo() {
		return usu_tel_fixo;
	}

	public void setUsu_tel_fixo(String usu_tel_fixo) {
		this.usu_tel_fixo = usu_tel_fixo;
	}

	public void setUsu_nome_end(String usu_nome_end) {
		this.usu_nome_end = usu_nome_end;
	}

	public void setUsu_cidade(String usu_cidade) {
		this.usu_cidade = usu_cidade;
	}

	public String getUsu_nome_end() {
		return usu_nome_end;
	}

	public String getUsu_cidade() {
		return usu_cidade;
	}

	public String getUsu_cep() {
		return usu_cep;
	}

	public void setUsu_cep(String usu_cep) {
		this.usu_cep = usu_cep;
	}

	public String getUsu_tipo_endereco() {
		return usu_tipo_endereco;
	}

	public void setUsu_tipo_endereco(String usu_tipo_endereco) {
		this.usu_tipo_endereco = usu_tipo_endereco;
	}

	public String getUsu_bairro() {
		return usu_bairro;
	}

	public void setUsu_bairro(String usu_bairro) {
		this.usu_bairro = usu_bairro;
	}

	
 
    public String getUsu_nome() {
		return usu_nome;
	}

	public void setUsu_nome(String usu_nome) {
		this.usu_nome = usu_nome;
	}

	public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getSenha() {
        return usu_senha;
    }
 
    public void setSenha(String usu_senha) {
        this.usu_senha = usu_senha;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
