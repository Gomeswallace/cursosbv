package com.wallacegomes.automacaoedificacoes.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Ambiente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name="AMBIENTE_EQUIPAMENTO",
		joinColumns = @JoinColumn(name="ambiente_id"),
		inverseJoinColumns = @JoinColumn(name="equipamento_id")
	)	
	private List<Equipamento> equipamentos = new ArrayList<>();
	
	@JsonBackReference
	@JsonIgnore
	@ManyToMany(mappedBy="ambientes") //indica que o mapeamento foi realizado do outro para equipamentos
	private List<Dispositivo> dispositivos = new ArrayList<>();
	
	/*
	@JsonIgnore
	@OneToMany(mappedBy = "ambiente")
	private List<Equipamento> equipamentos = new ArrayList<>();
	*/
	
	public Ambiente() {		
	}
	
	public Ambiente(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ambiente other = (Ambiente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
