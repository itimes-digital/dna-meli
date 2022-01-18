package br.com.meli.dna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PARTIAL_CONTENT)
public class ResourcePartialContentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6640433071922259671L;
	
	public ResourcePartialContentException(String exception) {
		super(exception);
	}

}
