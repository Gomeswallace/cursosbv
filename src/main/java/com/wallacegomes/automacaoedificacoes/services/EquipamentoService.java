package com.wallacegomes.automacaoedificacoes.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.repositories.EquipamentoRepository;

@Service
public class EquipamentoService {
			
	@Autowired //será instancia automaticamente pelo spring pela injecao de depencia ou inversao de controle
	public EquipamentoRepository repo;

	public Equipamento buscarPorId(Integer id) {
		Optional<Equipamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Equipamento.class.getName(), null));
	}
	
	public Equipamento insert(Equipamento obj) {
		//nulo faz insert, caso contrario faz update
		obj.setId(null);
		
		return repo.save(obj);
}
}
