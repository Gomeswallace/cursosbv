package com.wallacegomes.automacaoedificacoes.services;

import org.springframework.mail.SimpleMailMessage;

import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Dispositivo obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario cliente, String newPass);
}
