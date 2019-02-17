package com.wallacegomes.automacaoedificacoes.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/equipamentos")
public class EquipamentoResource {
	
	@RequestMapping(method=RequestMethod.GET)  
	public String listar() {
		return "REST está funcionando";
	}
}
