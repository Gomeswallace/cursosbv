package com.wallacegomes.automacaoedificacoes.domain.enums;

public enum TipoUsuario {
	ADMINISTRADOR(0),
	CLIENTE(1);
	
	private int cod;
	
	private TipoUsuario(int cod) {
		this.cod = cod;
	}
	
	public int getCod() {
		return cod;
	}
	
	public static TipoUsuario toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoUsuario t: TipoUsuario.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
