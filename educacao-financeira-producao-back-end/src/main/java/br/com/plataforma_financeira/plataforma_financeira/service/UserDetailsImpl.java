package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;
import lombok.Getter;

@Getter
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long usu_id;

	private String usu_nome;

	private String email;

	@JsonIgnore
	private String usu_senha;	
	
	
	private String usu_cep;
	
	
	private String usu_tipo_endereco;
	
	
	private String usu_nome_end;
	
	
	private String usu_numero;
	
	
	private String usu_bairro;
	

	private String usu_cidade;
	
	
	private String usu_uf;
	
	
	private String usu_tel_celular;
	
	
	private String usu_tel_fixo;
	
	
	
	private char usu_status;
	
	
	private String usu_complemento;		
	
	
	
	

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long usu_id, String usu_nome, String email, String usu_senha,
			Collection<? extends GrantedAuthority> authorities) {
		this.usu_id = usu_id;
		this.usu_nome = usu_nome;
		this.email = email;
		this.usu_senha = usu_senha;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Usuario user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getUsu_id(), 
				user.getUsu_nome(), 
				user.getEmail(),
				user.getUsu_senha(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return usu_id;
	}

	public String getEmail() {
		return email;
	}
	
	
	@Override
	public String getUsername() {		
		return usu_nome;
	}
	
	@Override
	public String getPassword() {		
		return usu_senha;
	}
	
	
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(usu_id, user.usu_id);
	}

	

	
}
