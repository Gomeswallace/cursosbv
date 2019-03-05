package com.wallacegomes.automacaoedificacoes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.dto.EquipamentoDTO;
import com.wallacegomes.automacaoedificacoes.services.EquipamentoService;

@RestController
@RequestMapping(value="/equipamentos")
public class EquipamentoResource {
	
	@Autowired //instanciar o objeto automaticamente
	private EquipamentoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Equipamento> find(@PathVariable Integer id){
		Equipamento obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//mapear o metodo no endpoin equipamentos como POST 
	@RequestMapping(method = RequestMethod.POST) //@RequestBody permite que o obj seja construido a partir do json enviado 	
	public ResponseEntity<Void> insert(@Valid @RequestBody EquipamentoDTO objDTO){
		Equipamento obj = service.fromDTO(objDTO);
				
		//ver http status code
		obj = service.insert(obj); 
		//o http tem seus codigos de retorno apos realizar a operacao
		//fromCurrentRequest pega a uri que foi utilizada para inserir e add o id
		//buildAndExpand pega o id do obj que foi inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value= "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> Update(@RequestBody Equipamento obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		//noContent conteudo vazio
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EquipamentoDTO>> findAll(){
		List<Equipamento> list = service.findAll();
		//lista de dto para o stream converter cada obj em dto pela funcao anonima e depois retornar essa lista
		List<EquipamentoDTO> listDTO = list.stream().map(obj -> new EquipamentoDTO(obj))
											.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<EquipamentoDTO>> findPage(
			 @RequestParam(value="page", defaultValue="0")  Integer page, 
			 @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			 @RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			 @RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Equipamento> list = service.findPage(page, linesPerPage, orderBy, direction);
		//lista de dto para o stream converter cada obj em dto pela funcao anonima e depois retornar essa lista
		Page<EquipamentoDTO> listDTO = list.map(obj -> new EquipamentoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
