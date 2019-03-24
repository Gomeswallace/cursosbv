package com.wallacegomes.automacaoedificacoes.services;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Dispositivo obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	//Envio de e-mail com html
	void sendOrderConfirmationHtmlEmail(Dispositivo obj);

	void sendHtmlEmail(MimeMessage msg);
}
