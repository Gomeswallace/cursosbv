package com.wallacegomes.automacaoedificacoes.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	//Utilizado para gerar log no console
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando o envio de e-mail...");
		LOG.info(msg.toString());
		//responsavel para envio do e-mail
		mailSender.send(msg);
		LOG.info("E-mail enviado com sucesso!!!");		
	}
	
	

	
}
