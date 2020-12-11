package br.com.plataforma_financeira.plataforma_financeira.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(	name = "usuario", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "usu_nome"),
			@UniqueConstraint(columnNames = "email") 
		})
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usu_id;

	@NotBlank
	@Size(max = 100)
	private String usu_nome;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@JsonIgnore
	@NotBlank
	@Size(max = 120)
	private String usu_senha;
	
	
	@Column(unique = true, length = 11)
	private String usu_cpf;	
	
	@Column(nullable = false, length = 8)
	private String usu_cep;
	
	@Column(nullable = false, length = 20)
	private String usu_tipo_endereco;
	
	@Column(nullable = false, length = 60)
	private String usu_nome_end;
	
	@Column(nullable = true)
	private String usu_numero;
	
	@Column(nullable = false, length = 50)
	private String usu_bairro;
	
	@Column(nullable = false, length = 50)
	private String usu_cidade;
	
	@Column(nullable = false, length = 2)
	private String usu_uf;
	
	@Column(nullable = false, length = 11)
	private String usu_tel_celular;
	
	@Column(nullable = true, length = 11)
	private String usu_tel_fixo;
	
	
	@Column(nullable = false)
	private char usu_status;
	
	@Column(nullable = true, length = 150)
	private String usu_complemento;		
	
	/*
	@OneToMany(mappedBy = "usuario")
	private List<Dependente> dependentes;	
	*/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "usuarios_roles", 
				joinColumns = @JoinColumn(name = "usu_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
		
	
	
	
	public Usuario() {
	}

	public Usuario(String usu_cpf, String usu_nome, String email, String usu_cep, String usu_tipo_endereco, 
			String usu_nome_end, String usu_numero, String usu_bairro, String usu_complemento, String usu_cidade, String usu_uf, String usu_tel_celular,
			String usu_tel_fixo, char usu_status, String usu_senha) {
		
		this.usu_cpf = usu_cpf;
		this.usu_nome = usu_nome;
		this.email = email;
		this.usu_cep = usu_cep;
		this.usu_tipo_endereco = usu_tipo_endereco;
		this.usu_nome_end = usu_nome_end;
		this.usu_numero = usu_numero;
		this.usu_complemento = usu_complemento;
		this.usu_bairro = usu_bairro;
		this.usu_cidade = usu_cidade;
		this.usu_uf = usu_uf;
		this.usu_tel_celular = usu_tel_celular;
		this.usu_tel_fixo = usu_tel_fixo;
		this.usu_status = usu_status;
		this.usu_senha = usu_senha;
	}	
	
	
	public Usuario(Long usu_id, String usu_cpf, String usu_nome, String email, String usu_cep, String usu_tipo_endereco, 
			String usu_nome_end, String usu_numero, String usu_bairro, String usu_complemento, String usu_cidade, String usu_uf, String usu_tel_celular,
			String usu_tel_fixo, char usu_status, String usu_senha) {
		
		this.usu_id = usu_id;
		this.usu_cpf = usu_cpf;
		this.usu_nome = usu_nome;
		this.email = email;
		this.usu_cep = usu_cep;
		this.usu_tipo_endereco = usu_tipo_endereco;
		this.usu_nome_end = usu_nome_end;
		this.usu_numero = usu_numero;
		this.usu_complemento = usu_complemento;
		this.usu_bairro = usu_bairro;
		this.usu_cidade = usu_cidade;
		this.usu_uf = usu_uf;
		this.usu_tel_celular = usu_tel_celular;
		this.usu_tel_fixo = usu_tel_fixo;
		this.usu_status = usu_status;
		this.usu_senha = usu_senha;
	}
	
	
	

	public void setEmail(String email) {
		this.email = email;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
