package br.com.meli.dna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDnaEnum {

	HUMANO(Boolean.FALSE),
	SIMIO(Boolean.TRUE);
	
	private Boolean tipoDna;

}
