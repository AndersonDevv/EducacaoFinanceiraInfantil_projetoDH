package br.com.plataforma_financeira.plataforma_financeira.service;

import org.springframework.mail.SimpleMailMessage;


import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Usuario usuario);
	void sendEmail(SimpleMailMessage msg);	
	void sendNewPasswordEmail(Usuario usuario, String newPass);

}
