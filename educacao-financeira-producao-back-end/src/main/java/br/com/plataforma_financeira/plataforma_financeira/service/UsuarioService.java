
package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;
import br.com.plataforma_financeira.plataforma_financeira.repository.UsuarioRepository;
import br.com.plataforma_financeira.plataforma_financeira.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailService emailService;
	
	public List<Usuario> findAll() {		
		return usuarioRepository.findAll();		
	}
	
	public Usuario buscarUsuarioPorId(Long id) {	
		
		//Esse código só permite o acesso ao usuário autorizado
		/*
		UserSS user = UserService.authenticated();
		if(user ==null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getUsua_id())) {
			throw new AuthorizationException("Acesso negado");
		}
		*/
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "));
		
	}
	
	
	public Usuario insert(Usuario usuario) {
		 //usuario.setUsu_cpf(null);
		//usuario.setUsu_senha(passwordEncode.encode(usuario.getUsu_senha()));
		
		usuario = usuarioRepository.save(usuario);
		//emailService.sendOrderConfirmationEmail(usuario);

	 /*

	        try {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper( mail );
	            helper.setTo( "luciano_dialog@yahoo.com.br" );
	            helper.setSubject( "Cadastro na Plataforma Financeira." );
	            helper.setText("<h1 style='color: blue;'>Seja Bem Vindo(a) ao Mundo da Educação Financeira.</h1><br>"  + usuario.getUsu_nome(), true);
	            mailSender.send(mail);
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	          
	        }
	     */   
	    

		
		
		return usuario;
	}
	
	public void deletarUsuario(Long usu_id) {
		usuarioRepository.deleteById(usu_id);
	}
	
	public Usuario update(Usuario usuario) {
		buscarUsuarioPorId(usuario.getUsu_id());
		return usuarioRepository.save(usuario);
	}	
	

	public Usuario findByEmail(String email) {		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);		
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! "));
	}
		

}
