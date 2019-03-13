package com.wallacegomes.automacaoedificacoes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wallacegomes.automacaoedificacoes.services.DBService;

@Configuration //indica que a classe é de configuração
@Profile("test") //indica o profile (dentro de resources) que este arquivo pertence
public class TestConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean instatiateDataBase() {
		
		service.instantiateTestDataBase();		
		return true;
	}
	
}
