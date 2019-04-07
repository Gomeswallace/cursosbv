package com.wallacegomes.automacaoedificacoes.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.wallacegomes.automacaoedificacoes.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			//retorna o usuario logado no sistema
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}


}
