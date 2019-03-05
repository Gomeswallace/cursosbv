package com.wallacegomes.automacaoedificacoes.dto;

import java.io.Serializable;

import com.wallacegomes.automacaoedificacoes.domain.Equipamento;

public class EquipamentoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public EquipamentoDTO() {		
	}
	
	//criado o construtor para converter o obj em dto
	public EquipamentoDTO(Equipamento obj) {
		id = obj.getId();
		nome = obj.getNome();
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

}
