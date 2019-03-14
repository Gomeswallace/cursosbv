package com.wallacegomes.automacaoedificacoes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wallacegomes.automacaoedificacoes.services.DBService;

@Configuration //indica que a classe é de configuração
@Profile("dev") //indica o profile (dentro de resources) que este arquivo pertence
public class DevConfig {
	
	@Autowired
	private DBService service;
	
	@Value("${spring.jpa.hibernate.ddl-auto}") //pega o valor da chave e salva na variavel
	private String strategy;
	
	@Bean
	public boolean instatiateDataBase() {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		service.instantiateTestDataBase();		
		return true;
	}
	
}
