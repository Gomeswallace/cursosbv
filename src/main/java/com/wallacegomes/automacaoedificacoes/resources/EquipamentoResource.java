package com.wallacegomes.automacaoedificacoes.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallacegomes.automacaoedificacoes.domain.Equipamento;

@RestController
@RequestMapping(value="/equipamentos")
public class EquipamentoResource {
	
	private Integer id;
	private String nome;
	
	@RequestMapping(method=RequestMethod.GET)  
	public List<Equipamento> listar() {
		Equipamento cat1 = new Equipamento(1, "Informatica");
		Equipamento cat2 = new Equipamento(1, "Escritorio");

		List<Equipamento> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);		

		return lista;
	}
}
