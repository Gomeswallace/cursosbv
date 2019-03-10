package com.wallacegomes.automacaoedificacoes.domain.enums;

public enum TipoEquipamento {
	LAMPADA(0, "Lampada"),
	TOMADA(1, "Tomada"),
	TV(2, "TV"),
	RADIO(3, "Radio"),
	VENTILADOR(4, "Ventilador");
	
	private int cod;
	private String nome;
	
	private TipoEquipamento(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}
	
	public static TipoEquipamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoEquipamento t: TipoEquipamento.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
