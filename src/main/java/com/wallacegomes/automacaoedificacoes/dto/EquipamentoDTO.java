package com.wallacegomes.automacaoedificacoes.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.wallacegomes.automacaoedificacoes.domain.Equipamento;
import com.wallacegomes.automacaoedificacoes.domain.enums.TipoEquipamento;;

public class EquipamentoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Length(min=5, max=80, message="O tamanho deve ser entre 3 e 80 caracteres!")
	private String nome;
	private Integer porta;
	private boolean status;
	private TipoEquipamento tipo; 
	
	public EquipamentoDTO() {		
	}
	
	//criado o construtor para converter o obj em dto
	public EquipamentoDTO(Equipamento obj) {
		id = obj.getId();
		nome = obj.getNome();
		porta = obj.getPorta();
		status = obj.isStatus();
		tipo = obj.getTipo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public TipoEquipamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoEquipamento tipo) {
		this.tipo = tipo;
	}

	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}	
}
