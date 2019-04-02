package com.wallacegomes.automacaoedificacoes.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.wallacegomes.automacaoedificacoes.domain.Dispositivo;

public class DispositivoDTO implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório!")	
	private String nome;
	
	@Length(min=5, max=80, message="O tamanho deve ser entre 3 e 80 caracteres!")
	private String descricao;
	
	public DispositivoDTO() {
		
	}
	
	public DispositivoDTO(Dispositivo obj) {
		id = obj.getId();
		nome = obj.getNome();
		descricao = obj.getDescricao();		
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
}
