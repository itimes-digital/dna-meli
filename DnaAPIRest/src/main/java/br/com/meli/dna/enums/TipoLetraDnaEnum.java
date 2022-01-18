package br.com.meli.dna.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoLetraDnaEnum {

	LETRA_A("A"),
	LETRA_T("T"),
	LETRA_C("C"),
	LETRA_G("G");
	
	private String tipoLetraDna;

}
