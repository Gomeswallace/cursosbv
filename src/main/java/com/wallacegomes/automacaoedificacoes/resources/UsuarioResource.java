package com.wallacegomes.automacaoedificacoes.resources;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;
import com.wallacegomes.automacaoedificacoes.dto.AmbienteDTO;
import com.wallacegomes.automacaoedificacoes.services.UsuarioService;

public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> buscaPorId(@PathVariable Integer id) {
		
		Usuario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Usuario obj){ //converte o obj em json
		obj = service.insert(obj);
		//pega o id do novo recurso criado e add na url
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		//retorna a nova url criada
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> update(@RequestBody Usuario obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();					
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AmbienteDTO>> findAll() {
		List<Usuario> list = service.findAll();
		//List<AmbienteDTO> listDTO = list.stream().map(obj -> new AmbienteDTO(obj)).collect(Collectors.toList());
				
		return ResponseEntity.ok(null);
	}
}
