package com.wallacegomes.automacaoedificacoes.domain.enums;

public enum TipoUsuario {
	//prefixo ROLE_ obrigatorio do framework spring
	ADMINISTRADOR(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	private TipoUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
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
