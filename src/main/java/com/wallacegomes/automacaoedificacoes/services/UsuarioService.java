package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import com.wallacegomes.automacaoedificacoes.domain.Ambiente;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;
import com.wallacegomes.automacaoedificacoes.repositories.UsuarioRepository;

public class UsuarioService {
	@Autowired
	public UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Ambiente.class.getName(), null));
	}	
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);;		
		return repo.save(obj);		
	}
	
	public Usuario update(Usuario obj) {
		find(obj.getId()); //verifica se o obj existe antes de tentar atualizar
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {			
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Não é possível excluir um Usuario!");
		}
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
}
}
