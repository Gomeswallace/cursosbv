package com.wallacegomes.automacaoedificacoes.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando o envio de e-mail...");
		LOG.info(msg.toString());
		//responsavel para envio do e-mail
		javaMailSender.send(msg);
		LOG.info("E-mail enviado com sucesso!!!");
		
	}
	
	

	
}
