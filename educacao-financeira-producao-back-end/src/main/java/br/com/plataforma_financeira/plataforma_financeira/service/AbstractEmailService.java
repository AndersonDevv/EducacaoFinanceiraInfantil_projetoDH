package br.com.plataforma_financeira.plataforma_financeira.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;



public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	

	@Override
	public void sendOrderConfirmationEmail(Usuario usuario) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(usuario);
		sendEmail(sm);		
	}	
	
	protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario usuario) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Cadastro Realizado! Código: " + usuario.getUsu_id());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(usuario.toString());
		return sm;
	}

	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	

}
