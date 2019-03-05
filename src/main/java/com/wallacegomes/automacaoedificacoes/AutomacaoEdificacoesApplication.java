package com.wallacegomes.automacaoedificacoes;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.wallacegomes.automacaoedificacoes.domain.Ambiente;
import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.domain.TipoEquipamento;
import com.wallacegomes.automacaoedificacoes.domain.Usuario;
import com.wallacegomes.automacaoedificacoes.domain.enums.TipoUsuario;
import com.wallacegomes.automacaoedificacoes.repositories.AmbienteRepository;
import com.wallacegomes.automacaoedificacoes.repositories.EquipamentoRepository;
import com.wallacegomes.automacaoedificacoes.repositories.TipoEquipamentoRepository;
import com.wallacegomes.automacaoedificacoes.repositories.UsuarioRepository;

@SpringBootApplication
public class AutomacaoEdificacoesApplication implements CommandLineRunner{

	@Autowired
	private TipoEquipamentoRepository tipoEquipamentoRepository;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	
	@Autowired
	private AmbienteRepository ambienteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AutomacaoEdificacoesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario user1 = new Usuario(null, "Wallace", "wallace@email.com", TipoUsuario.ADMINISTRADOR);
		user1.getTelefones().addAll(Arrays.asList("9999-9999"));
		
		TipoEquipamento tipo1 = new TipoEquipamento(null, "Lampada");
		TipoEquipamento tipo2 = new TipoEquipamento(null, "Tomada");

		Equipamento ep1 = new Equipamento(null, "Tomada", true, tipo1);
		Equipamento ep2 = new Equipamento(null, "Tomada Luz", false, tipo2);
		
		Ambiente amb1 = new Ambiente(null, "Sala TV", "Sala embaixo");
		Ambiente amb2 = new Ambiente(null, "Garagem", "Sala embaixo");
		
		tipo1.getEquipamentos().addAll(Arrays.asList(ep1));
		tipo2.getEquipamentos().addAll(Arrays.asList(ep2));
		
		ep1.getAmbientes().addAll(Arrays.asList(amb1));
		ep2.getAmbientes().addAll(Arrays.asList(amb2));
		
		amb1.getEquipamentos().addAll(Arrays.asList(ep1));
		amb2.getEquipamentos().addAll(Arrays.asList(ep2));
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		tipoEquipamentoRepository.saveAll(Arrays.asList(tipo1, tipo2));
		equipamentoRepository.saveAll(Arrays.asList(ep1, ep2));
		ambienteRepository.saveAll(Arrays.asList(amb1,amb2));
		
	}

}
