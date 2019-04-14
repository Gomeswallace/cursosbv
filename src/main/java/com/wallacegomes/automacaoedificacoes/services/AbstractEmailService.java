package com.wallacegomes.automacaoedificacoes.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	//pega o valor contindo no parametro e add na variavel. Neste caso vem do properties
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Dispositivo obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Dispositivo obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		//sm.setTo(obj.getUsuario().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
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
