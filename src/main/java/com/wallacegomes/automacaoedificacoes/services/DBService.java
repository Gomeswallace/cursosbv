package com.wallacegomes.automacaoedificacoes.services;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.wallacegomes.automacaoedificacoes.domain.Ambiente;
import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.domain.enums.TipoEquipamento;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;
import com.wallacegomes.automacaoedificacoes.domain.enums.TipoUsuario;
import com.wallacegomes.automacaoedificacoes.repositories.AmbienteRepository;
import com.wallacegomes.automacaoedificacoes.repositories.EquipamentoRepository;
import com.wallacegomes.automacaoedificacoes.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public void instantiateTestDataBase() {
	
		
		Usuario user1 = new Usuario(null, "Wallace", "gomes.wallace10@email.com", pe.encode("1234"), TipoUsuario.ADMINISTRADOR);
		user1.getTelefones().addAll(Arrays.asList("9999-9999"));
		
		Equipamento ep1 = new Equipamento(null, "Lampada", 1, true, TipoEquipamento.LAMPADA );
		Equipamento ep2 = new Equipamento(null, "Tomada Luz", 2, false, TipoEquipamento.TOMADA);
		
		Ambiente amb1 = new Ambiente(null, "Sala TV", "Sala embaixo");
		Ambiente amb2 = new Ambiente(null, "Garagem", "Sala embaixo");
		
		ep1.getAmbientes().addAll(Arrays.asList(amb1));
		ep2.getAmbientes().addAll(Arrays.asList(amb2));
		
		amb1.getEquipamentos().addAll(Arrays.asList(ep1));
		amb2.getEquipamentos().addAll(Arrays.asList(ep2));
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		equipamentoRepository.saveAll(Arrays.asList(ep1, ep2));
		ambienteRepository.saveAll(Arrays.asList(amb1,amb2));
		
	}
}
