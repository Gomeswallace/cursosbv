package com.wallacegomes.automacaoedificacoes.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;

public abstract class AbstractEmailService implements EmailService {

	//pega o valor contindo no parametro e add na variavel. Neste caso vem do properties
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Dispositivo obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromDispositivo(obj);
		sendEmail(sm);	 
	}
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	protected SimpleMailMessage prepareSimpleMailMessageFromDispositivo(Dispositivo obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("gomes.wallace10@gmail.com");
		sm.setFrom(sender);
		sm.setSubject("Dispositivo Adicionado");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Dispositivo obj) {
		Context context = new Context();
		//context atribui o valor do obj para a variavel dispositivo
		context.setVariable("dispositivo", obj);
		//template procura por padrao dentro de resources/templates o caminho para processar o arquivo
		return templateEngine.process("email/alteracaoDispositivo", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Dispositivo obj){
		try {
		MimeMessage mm = prepareMimeMessageFromDispositivo(obj);
		sendHtmlEmail(mm);
		}
		catch(MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	} 
	
	//metodo para atribuir o valor do obj na 
	protected MimeMessage prepareMimeMessageFromDispositivo(Dispositivo obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
		mmh.setTo(obj.getNome()); //obj.getCliente().getEmail()
		mmh.setFrom(sender);
		mmh.setSubject("Dispositivo alterado: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
}
