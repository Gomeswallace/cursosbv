package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import com.wallacegomes.automacaoedificacoes.domain.Ambiente;
import com.wallacegomes.automacaoedificacoes.dto.AmbienteDTO;
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
		obj.setId(null); //id null para garantir a insercao		
		return repo.save(obj);		
	}
	
	public Ambiente update(Ambiente obj) {
		Ambiente newObj = find(obj.getId()); //verifica se o obj existe antes de tentar atualizar
		updateData(newObj, obj); //Criado o metodo para tratar quais os dados podem ser atualizados
		return repo.save(newObj);
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
	
	public Page<Ambiente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Ambiente fromDTO(AmbienteDTO objDTO) {
			return new Ambiente(objDTO.getId(), objDTO.getNome(), objDTO.getDescricao());
	}
	
	private void updateData(Ambiente newObj, Ambiente obj) {
		newObj.setNome(obj.getNome());
		newObj.setDescricao(obj.getDescricao());
	}
}
