package com.wallacegomes.automacaoedificacoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.services.applicationautoscaling.model.ObjectNotFoundException;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;
import com.wallacegomes.automacaoedificacoes.domain.enums.TipoUsuario;
import com.wallacegomes.automacaoedificacoes.repositories.UsuarioRepository;
import com.wallacegomes.automacaoedificacoes.security.UserSS;
import com.wallacegomes.automacaoedificacoes.services.exceptions.AuthorizationException;
import com.wallacegomes.automacaoedificacoes.services.exceptions.DataIntegrityException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	//@Autowired
	//private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	//@Autowired
	//private S3Service s3Service;
	
	//@Autowired
	//private ImageService imageService;
		
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(TipoUsuario.ADMINISTRADOR) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		//enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<Usuario> findAll() {
		return repo.findAll();
	}
	
	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(TipoUsuario.ADMINISTRADOR) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	/*public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	*/
	
	public Usuario fromDTO(Usuario objDto) {
		Usuario cli = new Usuario(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		//Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		//Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		//cli.getEnderecos().add(end);
		//cli.getTelefones().add(objDto.getTelefone1());
		
		return cli;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	/*
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	*/
	
}
