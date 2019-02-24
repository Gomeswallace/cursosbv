package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.wallacegomes.automacaoedificacoes.domain.Ambiente;
import com.wallacegomes.automacaoedificacoes.repositories.AmbienteRepository;

public class AmbienteService {
	@Autowired
	public AmbienteRepository repo;
	
	public Ambiente find(Integer id) {
		Optional<Ambiente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Ambiente.class.getName(), null));
	}
	
	
	public Ambiente insert(Ambiente obj) {
		obj.setId(null);;		
		return repo.save(obj);		
	}
	
	public Ambiente update(Ambiente obj) {
		find(obj.getId()); //verifica se o obj existe antes de tentar atualizar
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {			
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Não é possível excluir um Ambiente que possui Recursos!");
		}
	}
	
	public List<Ambiente> findAll(){
		return repo.findAll();
}
}
