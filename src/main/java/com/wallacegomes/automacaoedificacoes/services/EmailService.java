package com.wallacegomes.automacaoedificacoes.services;

import org.springframework.mail.SimpleMailMessage;
import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Dispositivo obj);
	
	void sendEmail(SimpleMailMessage msg);
}
