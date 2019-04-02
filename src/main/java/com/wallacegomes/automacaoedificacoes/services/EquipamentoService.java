package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.dto.EquipamentoDTO;
import com.wallacegomes.automacaoedificacoes.repositories.EquipamentoRepository;
import com.wallacegomes.automacaoedificacoes.services.exceptions.DataIntegrityException;

@Service
public class EquipamentoService {
			
	@Autowired //será instancia automaticamente pelo spring pela injecao de depencia ou inversao de controle
	public EquipamentoRepository repo;

	public Equipamento find(Integer id) {
		Optional<Equipamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Equipamento.class.getName(), null));
	}
	
	public Equipamento insert(Equipamento obj) {
		//set id null para garantir uma insercao
		obj.setId(null);
		
		return repo.save(obj);
	}
	
	public Equipamento update(Equipamento obj) {
		Equipamento newObj = find(obj.getId()); //verifica se o obj existe antes de tentar atualizar
		updateData(newObj, obj); //Criado o metodo para tratar quais os dados podem ser atualizados
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);;
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível um equipamento que possui ambiente");
		}
	}
	
	public List<Equipamento> findAll() {
		return repo.findAll();
	}
	
	public Page<Equipamento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Equipamento fromDTO(EquipamentoDTO objDTO) {
			return new Equipamento(objDTO.getId(), objDTO.getNome(), objDTO.getPorta(), objDTO.isStatus(), objDTO.getTipo());
	}
	
	private void updateData(Equipamento newObj, Equipamento obj) {
		newObj.setNome(obj.getNome());
		newObj.setPorta(obj.getPorta());
		newObj.setStatus(obj.isStatus());
	}
}
