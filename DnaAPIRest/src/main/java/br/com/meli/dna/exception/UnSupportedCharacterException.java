package br.com.meli.dna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnSupportedCharacterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640433071922259671L;
	
	public UnSupportedCharacterException(String exception) {
		super(exception);
	}

}
