package com.wallacegomes.automacaoedificacoes.domain.enums;

public enum TipoDispositivo {
	ARDUINO_DUE(0, "Arduino Due", 12, 54),
	ARDUINO_LALYPAD(1, "Arduino Lalypad", 6, 14),
	ARDUINO_LEONARDO_ETHERNET(2, "Arduino Leonado", 7, 20),	
	ARDUINO_MEGA_2560(3, "Arduino Mega 2560", 16, 54),
	ARDUINO_NANO(4, "Arduino Nano", 8, 14),
	ARDUINO_UNO_R3(5, "Placa Uno R3", 6, 14);
	
	private int cod;
	private String nome;
	private Integer quantidadePortasAnalog;
	private Integer quantidadePortasDig;
	
	private TipoDispositivo(int cod, String nome, Integer quantidadePortasAnalog, Integer quantidadePortasDig) {
		this.cod = cod;
		this.nome = nome;
		this.quantidadePortasAnalog = quantidadePortasAnalog;
		this.quantidadePortasDig = quantidadePortasDig;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Integer getQuantidadePortasAnalog() {
		return quantidadePortasAnalog;
	}

	public Integer getQuantidadePortasDig() {
		return quantidadePortasDig;
	}

	public static TipoDispositivo toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoDispositivo t: TipoDispositivo.values()) {
			if(cod.equals(t.getCod())) {
				return t;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
