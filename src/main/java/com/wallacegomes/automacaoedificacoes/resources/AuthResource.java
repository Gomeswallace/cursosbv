package com.wallacegomes.automacaoedificacoes.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallacegomes.automacaoedificacoes.dto.EmailDTO;
import com.wallacegomes.automacaoedificacoes.security.JWTUtil;
import com.wallacegomes.automacaoedificacoes.security.UserSS;
import com.wallacegomes.automacaoedificacoes.services.AuthService;
import com.wallacegomes.automacaoedificacoes.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	//metodo protegido por autorizao, precisa logar 
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		//pega usuariologago
		UserSS user = UserService.authenticated();
		//gera um novo token com a data atual
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
		//retorna o objeto com a autorizacao e com o token
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
