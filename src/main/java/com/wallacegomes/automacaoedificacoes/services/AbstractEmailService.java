package com.wallacegomes.automacaoedificacoes.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;

public abstract class AbstractEmailService implements EmailService {

	//pega o valor contindo no parametro e add na variavel
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Dispositivo obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromDispositivo(obj);
		sendEmail(sm);	 
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromDispositivo(Dispositivo obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("gomes.wallace10@gmail.com");
		sm.setFrom(sender);
		sm.setSubject("Dispositivo Adicionado");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	
}
