package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;
import com.wallacegomes.automacaoedificacoes.repositories.DispositivoRepository;

public class DispositivoService {
	@Autowired
	private DispositivoRepository repo;
	
	@Autowired
	private EmailService emailService; 
	
	public Dispositivo find(Integer id) {
		Optional<Dispositivo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Dispositivo.class.getName(), null));
	}
	
	
	public Dispositivo insert(Dispositivo obj) {
		obj.setId(null); //id null para garantir a insercao		
		return repo.save(obj);		
	}
	
	public Dispositivo update(Dispositivo obj) {
		find(obj.getId()); //verifica se o obj existe antes de tentar atualizar
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {			
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Não é possível excluir um Dispositivo que possui Recursos!");
		}
	}
	
	public List<Dispositivo> findAll(){
		return repo.findAll();
	}
	
	public Page<Dispositivo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public void teste(Dispositivo obj) {
		emailService.sendOrderConfirmationEmail(obj);;
	}
	
	//public Dispositivo fromDTO(DispositivoDTO objDTO) {
	//		return new Dispositivo(objDTO.getId(), objDTO.getNome(), objDTO.getDescricao());
	//}
}
