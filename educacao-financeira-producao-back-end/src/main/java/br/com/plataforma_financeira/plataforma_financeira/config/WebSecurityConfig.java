package br.com.plataforma_financeira.plataforma_financeira.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import br.com.plataforma_financeira.plataforma_financeira.secutiry.jwt.AuthEntryPointJwt;
import br.com.plataforma_financeira.plataforma_financeira.secutiry.jwt.AuthTokenFilter;
import br.com.plataforma_financeira.plataforma_financeira.service.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//Indica quais os caminhos que por padrão estão liberados para requisições GET
	private static final String[] PUBLIC_ENDPOINTS_GET = {
			"/api/v1/categoria/**",
			"/api/v1/usuario/**",
			"/api/v1/tarefa/**",
			"/api/v1/dependente/**",
			"/api/v1/dias_semana/**",
			"/api/v1/usuario/login/{usu_email}/{usu_senha}"
			
	};
	
	//Permite que um usuário possa se cadastrar sem estar autenticado
	private static final String[] PUBLIC_ENDPOINTS_POST = {			
			"/api/v1/categoria/**",
			"/api/v1/usuario/**",
			"/api/v1/dependente/**",
			"/login/"
			
			
			
	};
	
    private static final String[] PUBLIC_ENDPOINTS = {
			
			"/api/v1/usuario/**",
    		"/auth/forgot/**",
			"/api/v1/tarefa/**",
			"/api/v1/dependente/**",
			"/api/v1/dias_semana/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/v1/login/**").permitAll()		
			.antMatchers(HttpMethod.POST, "/api/v1/usuario/**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/usuario").permitAll()
			.antMatchers(HttpMethod.POST, "/api/v1/dependente/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/v1/categoria/**").permitAll()

		    .anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}





























/*
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.plataforma_financeira.plataforma_financeira.security.JWTAuthenticationFilter;
import br.com.plataforma_financeira.plataforma_financeira.security.JWTAuthorizationFilter;
import br.com.plataforma_financeira.plataforma_financeira.security.JWTUtil;



@Configuration//Indica que é uma classe de configuração
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// Aqui é injetada a interface. O Spring busca a implementação adequada.
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private JWTUtil jwtUtil;
	
	//Indica quais os caminhos que por padrão estão liberados para requisições GET
	private static final String[] PUBLIC_ENDPOINTS_GET = {
			"/api/v1/categoria/**",
			"/api/v1/usuario/**",
			"/api/v1/tarefa/**",
			"/api/v1/dependente/**",
			"/api/v1/dias_semana/**",
			"/api/v1/usuario/login/{usu_email}/{usu_senha}"
			
	};
	
	//Permite que um usuário possa se cadastrar sem estar autenticado
	private static final String[] PUBLIC_ENDPOINTS_POST = {			
			"/api/v1/categoria/**",
			"/api/v1/usuario/**",
			"/api/v1/dependente/**",
			"/login/"
			
			
			
	};
	
    private static final String[] PUBLIC_ENDPOINTS = {
			
			"/api/v1/usuario/**",
    		"/auth/forgot/**",
			"/api/v1/tarefa/**",
			"/api/v1/dependente/**",
			"/api/v1/dias_semana/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		 //executa o método cors (que foi implementado abaixo) e também desabilita o
		 //esquema de segurança para aplicações que armazenam estado (nossa aplicação é
		 //stateless, por isso esse recurso é desnecessário)
		 
		
		//Chama o método de cors - corsConfigurationSource() - e desabilita a proteção de ataques crfs, já que não temos sessão ativa		
		http.cors().and().csrf().disable();
		//Autoriza os vetores passados para requisições se authenticação
		http.authorizeRequests()
		   //Permite todos os métodos "POST", "GET", "PUT", "DELETE", "OPTIONS"... para o vetor passado
		   //.antMatchers(PUBLIC_ENDPOINTS_GET).permitAll()	
			.antMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS_GET).permitAll()	
			.antMatchers(HttpMethod.PUT,PUBLIC_ENDPOINTS_GET).permitAll()	
			.antMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_POST).permitAll()	
			.anyRequest().authenticated();
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		// Assegura que o backend não criará sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//Bean de cors que permite o acesso aos endpoint de origens diferentes. Por isso o "/**"
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));//Liber PUT e DELETE para requisições autorizadas
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	
	 // Nesse método define-se quem é o UserDetailService e o algorítmo de
	  //criptografia de senha usado.
	 
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	

	//Encoda a senha para poder salvar no banco de dados
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
*/
